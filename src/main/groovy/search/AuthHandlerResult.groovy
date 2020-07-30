package search

class AuthHandlerResult {
    int errorCode;
    boolean success;
    String message;

    def failed(msg){
        success = false
        errorCode = 401
        message= msg
        return this
    }

    def succeeded(){
        success = true
        return this
    }
}
