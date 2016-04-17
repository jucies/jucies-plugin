package com.github.jucies.plugin;

import hudson.model.UpdateSite;
import hudson.util.FormValidation;
import jenkins.util.JSONSignatureValidator;
import net.sf.json.JSONObject;

import javax.annotation.Nonnull;
import java.io.IOException;

/**
 * UpdateSite without the signature. Please, use it carefully and make sure that your URL is secure.
 *
 */
public class SignaturelessUpdateSite extends UpdateSite {

    public SignaturelessUpdateSite(String id, String url) {
        super(id, url);
    }

    @Nonnull
    @Override
    protected JSONSignatureValidator getJsonSignatureValidator() {
        return new JSONSignatureValidator("No-op validator") {
            @Override
            public FormValidation verifySignature(JSONObject o) throws IOException {
                // Skip signature verification. We use https to connect, it should prevent MiTM attacks.
                return FormValidation.ok();
            }
        };
    }
}
