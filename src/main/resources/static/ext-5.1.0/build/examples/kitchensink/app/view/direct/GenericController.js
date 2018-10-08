Ext.define('KitchenSink.view.direct.GenericController', {
    extend: 'KitchenSink.view.direct.DirectVC',
    alias: 'controller.directgeneric',
    
    requires: [
        'Ext.direct.PollingProvider'
    ],
    
    config: {
        // This configuration is used to create Polling provider
        pollingCfg: {
            type: 'polling',
            url: 'resources/direct/poll.php'
        },
        
        listen: {
            controller: {
                '#Direct': {
                    directeventdata: 'onDirectEventData'
                }
            }
        }
    },
    
    finishInit: function() {
        var me = this,
            options = {};
        
        if (!me.finishInitReentry) {
            options.provider = me.getPollingCfg();
            
            // Work around reentrance
            me.finishInitReentry = true;
            me.requestUrl = options.provider.url;
        
            // Synchronously request global Direct controller to add
            // polling provider with the specified configuration.
            me.fireEvent('directconnect', options);
        
            if (options.success) {
                me.onProviderInit(options.url, true);
            }
        }
        else {
            delete me.finishInitReentry;
            
            me.trulyFinishInit();
        }
    },
    
    trulyFinishInit: function() {
        var me = this,
            urls = me.providerUrls,
            options, provider, view, updateFn;
        
        options = { url: urls[urls.length - 1] };
        
        me.fireEvent('directgetprovider', options);
        
        provider = options.provider;
        
        // The Polling provider will post `data` events when something
        // is received from server side, and global Direct controller
        // will relay those events as `directeventdata`. However there
        // may be more than one Polling provider and we are only interested
        // in the events from "our" one, so we save a reference to it
        // to filter events later.
        if (provider) {
            me.pollingProvider = provider;
            
            // This may come early, before the view is rendered.
            // Also note that the provider is disconnected but not destroyed
            // when a view no longer needs it, so the polling interval set
            // previously will be reused.
            updateFn = function() {
                me.updateView('Polling interval set to ' + 
                              (provider.interval / 1000) + ' seconds');
            };
            
            view = me.getView();
            
            if (view.rendered) {
                updateFn();
            }
            else {
                view.on({
                    afterrender: updateFn,
                    single: true
                });
            }
        }
    },
    
    destroy: function() {
        this.pollingProvider = null;
        
        this.callParent();
    },
    
    updateView: function(content) {
        var view = this.getView();
        
        view.update({ data: content });
        view.getTargetEl().scroll('b', 100000, true);
    },
    
    onFieldSpecialKey: function(field, event) {
        if (event.getKey() === event.ENTER) {
            this[field.directAction](field);
        }
    },
    
    onButtonClick: function(button) {
        var field = this.lookupReference(button.fieldReference);
        
        this[field.directAction](field);
    },
    
    setInterval: function(field) {
        var interval, provider;
        
        interval = parseInt(field.getValue());
        
        if (Ext.isNumeric(interval)) {
            provider = this.pollingProvider;
            
            provider.disconnect();
            
            // Accidentally (or intentionally) setting the interval to 0
            // will make the Polling provider to go frenzy and may cause
            // the browser to hang. So guard against it here.
            if (interval > 0) {
                provider.interval = interval * 1000;
                provider.connect();
                
                this.updateView('Polling interval set to ' + interval + ' seconds');
            }
            else {
                this.updateView('Polling was paused');
            }
        }
    },
    
    doEcho: function(field) {
        TestAction.doEcho(field.getValue(), this.onEcho, this, { field: field });
    },
    
    onEcho: function(result, event, success, options) {
        var transaction, content;
        
        transaction = event.getTransaction();
        
        content = Ext.String.format(
            '<b>Successful call to {0}.{1} with response:</b> <pre>{2}</pre>',
            transaction.action, transaction.method, Ext.encode(result)
        );
        
        this.updateView(content);
        
        options.field.reset();
    },
    
    doMultiply: function(field) {
        TestAction.multiply(field.getValue(), this.onMultiply, this, { field: field });
    },
    
    onMultiply: function(result, event, success, options) {
        var transaction, content;
        
        transaction = event.getTransaction();
            
        if (event.status) {
            content = Ext.String.format(
                '<b>Successful call to {0}.{1} with response:</b><pre>{2}</pre>',
                transaction.action, transaction.method, Ext.encode(result)
            );
        } else {
            content = Ext.String.format(
                '<b>Call to {0}.{1} failed with message:</b><pre>{2}</pre>',
                transaction.action, transaction.method, event.message
            );
        }
        
        this.updateView(content);
        
        options.field.reset();
    },
    
    onDirectEventData: function(provider, event) {
        if (provider === this.pollingProvider) {
            this.updateView('<i>' + event.data + '</i>');
        }
    }
});
