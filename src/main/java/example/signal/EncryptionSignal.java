package example.signal;

import example.signal.payloaddef.EncryptRequestParameter;
import rkernel.signal.BasicSignal;

public class EncryptionSignal extends BasicSignal<EncryptRequestParameter> {
    public EncryptionSignal(String type, EncryptRequestParameter payload) {
        super(type, payload);
    }
}
