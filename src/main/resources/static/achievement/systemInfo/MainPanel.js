Ext.define('Score.systemInfo.MainPanel', {
  extend: 'Ext.tab.Panel',
  alias: 'widget.scoreMainPanel',
  autoScroll: true,
  border: false,
  initComponent: function() {
    var me = this;
    me.items = [me.schoolInfoPanel(), me.teacherInfoPanel(), me.gradeInfoPanel(),
      me.classInfoPanel(), me.yearInfoPanel(), me.semesterInfoPanel(), me.parentInfoPanel()];
    me.callParent();
  },
  /**
   * 学校信息
   * @returns {{xtype: string, title: string, border: boolean}}
   */
  schoolInfoPanel: function() {
    var me = this;
    return {
      xtype: 'panel',
      title: '学校信息',
      border: false
    };
  },
  /**
   * 教师信息
   * @returns {{xtype: string, title: string, border: boolean}}
   */
  teacherInfoPanel: function() {
    var me = this;
    return {
      xtype: 'panel',
      title: '教师信息',
      border: false
    };
  },
  /**
   * 班级信息
   * @returns {{xtype: string, title: string, border: boolean}}
   */
  classInfoPanel: function() {
    var me = this;
    return {
      xtype: 'panel',
      title: '班级信息',
      border: false
    };
  },
  /**
   * 年级信息
   * @returns {{xtype: string, title: string, border: boolean}}
   */
  gradeInfoPanel: function() {
    var me = this;
    return {
      xtype: 'panel',
      title: '年级信息',
      border: false
    };
  },
  /**
   * 学年信息
   * @returns {{xtype: string, title: string, border: boolean}}
   */
  yearInfoPanel: function() {
    var me = this;
    return {
      xtype: 'panel',
      title: '学年信息',
      border: false
    };
  },
  /**
   * 学期信息
   * @returns {{xtype: string, title: string, border: boolean}}
   */
  semesterInfoPanel: function() {
    var me = this;
    return {
      xtype: 'panel',
      title: '学期信息',
      border: false
    };
  },
  /**
   * 家长信息
   * @returns {{xtype: string, title: string, border: boolean}}
   */
  parentInfoPanel: function() {
    var me = this;
    return {
      xtype: 'panel',
      title: '家长信息',
      border: false
    };
  }
});

