
/**
 * AccountWSCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package uiip.ws;

    /**
     *  AccountWSCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class AccountWSCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public AccountWSCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public AccountWSCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for ritornaAccount method
            * override this method for handling normal response from ritornaAccount operation
            */
           public void receiveResultritornaAccount(
                    uiip.ws.AccountWSStub.RitornaAccountResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from ritornaAccount operation
           */
            public void receiveErrorritornaAccount(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cancellaAccount method
            * override this method for handling normal response from cancellaAccount operation
            */
           public void receiveResultcancellaAccount(
                    uiip.ws.AccountWSStub.CancellaAccountResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cancellaAccount operation
           */
            public void receiveErrorcancellaAccount(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for listaAccountOffset method
            * override this method for handling normal response from listaAccountOffset operation
            */
           public void receiveResultlistaAccountOffset(
                    uiip.ws.AccountWSStub.ListaAccountOffsetResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from listaAccountOffset operation
           */
            public void receiveErrorlistaAccountOffset(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for numeroTotUtenti method
            * override this method for handling normal response from numeroTotUtenti operation
            */
           public void receiveResultnumeroTotUtenti(
                    uiip.ws.AccountWSStub.NumeroTotUtentiResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from numeroTotUtenti operation
           */
            public void receiveErrornumeroTotUtenti(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for modificaAccount method
            * override this method for handling normal response from modificaAccount operation
            */
           public void receiveResultmodificaAccount(
                    uiip.ws.AccountWSStub.ModificaAccountResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from modificaAccount operation
           */
            public void receiveErrormodificaAccount(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for inserisciAccount method
            * override this method for handling normal response from inserisciAccount operation
            */
           public void receiveResultinserisciAccount(
                    uiip.ws.AccountWSStub.InserisciAccountResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from inserisciAccount operation
           */
            public void receiveErrorinserisciAccount(java.lang.Exception e) {
            }
                


    }
    