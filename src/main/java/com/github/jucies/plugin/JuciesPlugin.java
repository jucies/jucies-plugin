package com.github.jucies.plugin;

import hudson.init.Initializer;
import jenkins.model.Jenkins;

import java.io.IOException;

public class JuciesPlugin {

    @Initializer
    public static void init() throws IOException {
        // TODO alpha & beta channels
        Jenkins.getInstance().getUpdateCenter().getSites().add(new SignaturelessUpdateSite("jucies", "https://jucies.github.io/update-center.json"));
    }
}
