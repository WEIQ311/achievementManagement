/**
 * This Controller handles initialization requests for the Ext.Direct API
 * shared by different examples; in itself it is an example of dynamic
 * API loading.
 */

Ext.define('KitchenSink.controller.Direct', {
    extend: 'Ext.app.Controller',
    
    requires: [
        'Ext.direct.RemotingProvider',
        'Ext.direct.Manager'
    ],
    
    config: {
        /**
         * @cfg {String} apiUrl
         * Default URL to use for Ext.Direct service discovery requests.
         */
        apiUrl: 'resources/direct/api.php',
        
        /**
         * @cfg {String} [varName=Ext.app.REMOTING_API]
         * Default variable name to use for Ext.Direct API declaration.
         */
        varName: 'Ext.app.REMOTING_API',
        
        listen: {
            controller: {
                // We're listening to these events from any Controller.
                // That includes ViewControllers, too.
                '*': {
                    directconnect: 'onDirectConnect',
                    directdisconnect: 'onDirectDisconnect',
                    directgetprovider: 'onDirectGetProvider'
                }
            }
        }
    },
    
    init: function() {
        this.providers = {};
    },
    
    /**
     * Request remote API from the server, and create a new Direct provider
     * when API declaration is received; alternatively create a new provider
     * if the caller provided a configuration blob for it.
     *
     * @param {Object} options Options to configure Provider request, and to
     * return to the caller in synchronous fashion.
     */
    onDirectConnect: function(options) {
        var me = this,
            providers = me.providers,
            url, varName, provider, providerCfg, requestId;
        
        // No defaults for this one
        providerCfg = options.provider;
        
        url = (providerCfg && providerCfg.url) || options.apiUrl  || me.getApiUrl();
        
        // The provider at that URI may have been initialized already
        provider = providers[url];
        
        options.url = url;
        
        // We hold the number of subscribers using this provider
        // and will disconnect it when it reaches 0. That also means
        // that we need to connect it again when someone requests it.
        if (provider) {
            if (!provider.subscribers) {
                provider.connect();
                provider.subscribers = 1;
            }
            else {
                provider.subscribers++;
            }
            
            options.success = true;
            
            return;
        }
        
        // We may be passed a provider configuration object, so instead of
        // fetching it from the server, add the provider directly.
        if (providerCfg && providerCfg.type && providerCfg.url) {
            me.onDirectApiLoad(providerCfg.url, {}, providerCfg);
        }
        else {
            varName = options.varName || me.getVarName();
        
            Ext.Loader.loadScript({
                url: url,
            
                // We have to use closures here as Loader does not support
                // passing options object from caller to callback.
                onLoad: function() {
                    this.onDirectApiLoad(url, varName, providerCfg);
                },
            
                onError: function() {
                    this.onDirectApiFailure(url);
                },
            
                scope: me
            });
        }
    },
    
    /**
     * Request to disconnect a Direct provider. This event is fired
     * by ViewControllers being destroyed, to notify Direct controller
     * that they no longer need the specified provider.
     *
     * @param {String} url Service URL for a provider
     */
    onDirectDisconnect: function(url) {
        var provider = this.providers[url];
        
        if (provider) {
            if (provider.subscribers > 0) {
                provider.subscribers--;
                
                if (provider.subscribers === 0) {
                    provider.disconnect();
                }
            }
        }
    },
    
    /**
     * Return a provider by its URL to the caller.
     */
    onDirectGetProvider: function(options) {
        options.provider = this.providers[options.url];
    },
    
    onDirectApiLoad: function(apiUrl, varName, providerCfg) {
        var me = this,
            api, provider, error;
        
        // Variable name could be nested (default is Ext.app.REMOTING_API),
        // so we use eval() to get the value.
        api = eval(varName);
        
        if (api) {
            try {
                api = Ext.apply({}, api, providerCfg);
                
                provider = Ext.direct.Manager.addProvider(api);
                
                // Direct Manager will connect the provider automatically
                // upon creation, so we just prime the counter
                provider.subscribers = 1;
                
                me.providers[apiUrl] = provider;
                
                // Polling providers will poll server side for events
                // periodically, and fire `data` events when something
                // is received. We relay these events here so that the
                // consumer ViewControllers did not have to bind to the
                // Polling controller directly.
                if (provider.type === 'polling') {
                    me.relayEvents(provider, ['data'], 'directevent');
                }
            }
            catch (e) {
                error = e + '';
            }
        }
        else {
            error = 'Cannot resolve Ext.Direct variable name: ' + varName;
        }
        
        if (error) {
            this.fireEvent('providerfail', apiUrl, error);
        }
        else {
            this.fireEvent('providerinit', apiUrl);
        }
    },
    
    onDirectApiFailure: function(apiUrl, error) {
        error = error || "Ext.Direct API was not found at " + apiUrl;
        
        this.fireEvent('providerfail', apiUrl, error);
    }
});
