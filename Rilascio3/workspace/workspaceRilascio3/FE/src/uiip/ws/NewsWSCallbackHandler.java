
/**
 * NewsWSCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package uiip.ws;

    /**
     *  NewsWSCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class NewsWSCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public NewsWSCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public NewsWSCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for rilasciaNotizie method
            * override this method for handling normal response from rilasciaNotizie operation
            */
           public void receiveResultrilasciaNotizie(
                    uiip.ws.NewsWSStub.RilasciaNotizieResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from rilasciaNotizie operation
           */
            public void receiveErrorrilasciaNotizie(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for numeroTotNotizie method
            * override this method for handling normal response from numeroTotNotizie operation
            */
           public void receiveResultnumeroTotNotizie(
                    uiip.ws.NewsWSStub.NumeroTotNotizieResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from numeroTotNotizie operation
           */
            public void receiveErrornumeroTotNotizie(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cancellaNotizia method
            * override this method for handling normal response from cancellaNotizia operation
            */
           public void receiveResultcancellaNotizia(
                    uiip.ws.NewsWSStub.CancellaNotiziaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cancellaNotizia operation
           */
            public void receiveErrorcancellaNotizia(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for ritornaNotizia method
            * override this method for handling normal response from ritornaNotizia operation
            */
           public void receiveResultritornaNotizia(
                    uiip.ws.NewsWSStub.RitornaNotiziaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from ritornaNotizia operation
           */
            public void receiveErrorritornaNotizia(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for notizia_in_modifica method
            * override this method for handling normal response from notizia_in_modifica operation
            */
           public void receiveResultnotizia_in_modifica(
                    uiip.ws.NewsWSStub.Notizia_in_modificaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from notizia_in_modifica operation
           */
            public void receiveErrornotizia_in_modifica(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for modificaNotizia method
            * override this method for handling normal response from modificaNotizia operation
            */
           public void receiveResultmodificaNotizia(
                    uiip.ws.NewsWSStub.ModificaNotiziaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from modificaNotizia operation
           */
            public void receiveErrormodificaNotizia(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for annullaModifica method
            * override this method for handling normal response from annullaModifica operation
            */
           public void receiveResultannullaModifica(
                    uiip.ws.NewsWSStub.AnnullaModificaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from annullaModifica operation
           */
            public void receiveErrorannullaModifica(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for inserisciNotizia method
            * override this method for handling normal response from inserisciNotizia operation
            */
           public void receiveResultinserisciNotizia(
                    uiip.ws.NewsWSStub.InserisciNotiziaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from inserisciNotizia operation
           */
            public void receiveErrorinserisciNotizia(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for visualizza_per_parametro_offset method
            * override this method for handling normal response from visualizza_per_parametro_offset operation
            */
           public void receiveResultvisualizza_per_parametro_offset(
                    uiip.ws.NewsWSStub.Visualizza_per_parametro_offsetResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from visualizza_per_parametro_offset operation
           */
            public void receiveErrorvisualizza_per_parametro_offset(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for listaNotizeOffset method
            * override this method for handling normal response from listaNotizeOffset operation
            */
           public void receiveResultlistaNotizeOffset(
                    uiip.ws.NewsWSStub.ListaNotizeOffsetResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from listaNotizeOffset operation
           */
            public void receiveErrorlistaNotizeOffset(java.lang.Exception e) {
            }
                


    }
    