Ext.define('Score.indexInfo.MainPanel', {
  extend: 'Ext.grid.Panel',
  alias: 'widget.indexInfoPanel',
  autoScroll: true,
  border: false,
  columnLines: true,
  viewConfig: {
    enableTextSelection: true
  },
  initComponent: function() {
    var me = this;
    me.items = [me.classInfoPanel(), me.gradeInfoPanel()];
    me.callParent();
  },
  classInfoPanel: function() {
    var me = this;
    return {
      xtype: 'panel',
      title: '班级信息',
      border: false
    };
  },
  gradeInfoPanel: function() {
    var me = this;
    return {
      xtype: 'panel',
      title: '年级信息',
      border: false
    };
  }
});

