Ext.define('Score.subjectInfo.MainPanel', {
  extend: 'Ext.tab.Panel',
  // alias: 'widget.scoreMainPanel',
  autoScroll: true,
  border: false,
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
  gradeInfoPanel:function() {
    var me = this;
    return {
      xtype: 'panel',
      title: '年级信息',
      border: false
    };
  }
});

