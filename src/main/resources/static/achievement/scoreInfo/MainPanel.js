Ext.define('Score.scoreInfo.MainPanel', {
  extend: 'Ext.grid.Panel',
  alias: 'widget.scoreInfoPanel',
  autoScroll: true,
  border: false,
  columnLines: true,
  viewConfig: {
    //可以选择
    enableTextSelection: true
  },
  pageSize: 30,
  initComponent: function() {
    var me = this;
    me.createStore();
    me.createColumns();
    me.createToolBar();
    me.callParent();
  },
  /**
   * store信息
   */
  createStore: function() {
    var me = this;
    me.store = Ext.create('Ext.data.Store', {
      fields: ['studentId', 'studentName'],
      autoLoad: true,
      pageSize: me.pageSize,
      remoteSore: true,
      actionMethods: {
        'read': 'get'
      },
      proxy: {
        type: 'ajax',
        url: '/score/scoreInfo/listByPage',
        timeout: 60 * 1000,
        reader: {
          type: 'json',
          root: 'data'
        }
      },
      listeners: {
        'load': function(thiz, records, successful, eOpts) {

        },
        'beforeload': function(store, opt, options) {
          console.log(me.store.page);
          var params = {
            studentName: me.down('#studentNameQueryItemId').getValue() || '',
            subjectId: me.down('#subjectIdQueryItemId').getValue() || ''
          };
          Ext.apply(store.proxy.extraParams, params);
        }
      }
    });
  },
  /**
   * 列信息
   */
  createColumns: function() {
    var me = this;
    me.columns = {
      defaults: {align: 'center', flex: 0.3},
      items: [{
        xtype: 'rownumberer',
        text: '序号',
        width: 50,
        maxWidth: 50
      }, {
        text: '班级',
        dataIndex: 'className',
        renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
          return (record.get('gradeName') || '') + value;
        }
      }, {
        text: '学期',
        dataIndex: 'semesterName',
        renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
          return (record.get('yearName') || '') + value;
        }
      }, {
        text: '科目',
        dataIndex: 'subjectName'
      }, {
        text: '代课教师',
        dataIndex: 'teacherName'
      }, {
        text: '学生姓名',
        dataIndex: 'studentName'
      }, {
        text: '成绩',
        dataIndex: 'scoreNumber'
      }, {
        text: '班级排名',
        dataIndex: 'classRanking'
      }, {
        text: '年级排名',
        dataIndex: 'gradeRanking'
      }, {
        text: '班级最高分',
        hidden: true,
        dataIndex: 'maxScore'
      }, {
        text: '班级平均分',
        hidden: true,
        dataIndex: 'avgScore'
      }, {
        text: '班级最低分',
        hidden: true,
        dataIndex: 'minScore'
      }, {
        text: '年级最高分',
        hidden: true,
        dataIndex: 'gradeMaxScore'
      }, {
        text: '年级平均分',
        hidden: true,
        dataIndex: 'gradeAvgScore'
      }, {
        text: '年级最低分',
        hidden: true,
        dataIndex: 'gradeMinScore'
      }]

    };
  },
  /**
   * toolBar
   */
  createToolBar: function() {
    var me = this;
    me.dockedItems = [{
      xtype: 'toolbar',
      dock: 'top',
      defaults: {
        labelAlign: 'right'
      },
      items: [{
        name: 'studentName',
        fieldLabel: '学生姓名',
        itemId: 'studentNameQueryItemId',
        xtype: 'textfield',
        enableKeyEvents: true,
        listeners: {
          'keydown': function(thiz, event, eOpts) {
            if (event.keyCode == 13) {
              me.queryStore();
            }
          }
        }
      }, {
        xtype: 'combobox',
        name: 'subjectId',
        fieldLabel: '科目',
        labelWidth: 60,
        width: 150,
        value: '',
        editable: false,
        itemId: 'subjectIdQueryItemId',
        store: Ext.create('Ext.data.Store', {
          fields: ['subjectId', 'subjectName'],
          autoLoad: true,
          actionMethods: {
            'read': 'get'
          },
          proxy: {
            type: 'ajax',
            url: '/score/subjectInfo/list',
            timeout: 60 * 1000,
            reader: {
              type: 'json',
              root: 'data'
            }
          },
          listeners: {
            'load': function(thiz, records, successful, eOpts) {
              thiz.insert(0, {
                'subjectId': '',
                'subjectName': '全部'
              });
            }
          }
        }),
        queryMode: 'local',
        displayField: 'subjectName',
        valueField: 'subjectId',
        listeners: {
          'change': function(thiz, newValue, oldValue, eOpts) {
            me.queryStore();
          }
        }
      }, '->', {
        xtype: 'button',
        text: '查询',
        handler: function() {
          me.queryStore();
        }
      }]
    }, {
      xtype: 'pagingtoolbar',
      store: me.store,
      dock: 'bottom',
      displayMsg: '显示{0}-{1}条,共{2}条',
      emptyMsg: '无数据',
      firstText: null,
      prevText: null,
      nextText: null,
      lastText: null,
      refreshText: null,
      displayInfo: true,
      items: ['-', {
        xtype: 'combobox',
        fieldLabel: '每页显示',
        labelWidth: 60,
        width: 150,
        value: 30,
        store: Ext.create('Ext.data.Store', {
          fields: ['name'],
          data: [{
            'name': me.pageSize
          }, {
            'name': '50'
          }, {
            'name': '100'
          }]
        }),
        queryMode: 'local',
        displayField: 'name',
        valueField: 'name',
        listeners: {
          'change': function(thiz, newValue, oldValue, eOpts) {
            thiz.up().store.currentPage = 1;
            me.changePage(newValue);
          }
        }
      }]
    }];
  },
  /**
   * 分页大小调整
   * @param value
   */
  changePage: function(value) {
    var me = this;
    me.store.pageSize = value;
    me.store.load();
  },
  /**
   * 查询方法
   */
  queryStore: function() {
    var me = this;
    me.store.currentPage = 1;
    me.store.load();
  }
});

