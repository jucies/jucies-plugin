package com.github.jucies.plugin;

import hudson.Extension;
import hudson.init.Initializer;
import hudson.model.UpdateCenter;
import jenkins.YesNoMaybe;
import jenkins.model.Jenkins;

import java.io.IOException;

import static hudson.init.InitMilestone.PLUGINS_STARTED;

@Extension(dynamicLoadable = YesNoMaybe.NO)
public class JuciesPlugin {

    @Initializer(after = PLUGINS_STARTED)
    public static void init() throws IOException {
        UpdateCenter updateCenter = Jenkins.getInstance().getUpdateCenter();

        boolean flush = false;

        // TODO alpha & beta channels
        Channel[] channels = {
                new Channel("jucies", "https://jucies.github.io/update-center.json")
        };

        for (Channel channel : channels) {
            if (updateCenter.getById(channel.id) != null) {
                continue;
            }

            flush = true;
            updateCenter.getSites().add(new SignaturelessUpdateSite(channel.id, channel.url));
        }

        if (flush) {
            updateCenter.save();
        }
    }

    private static final class Channel {
        final String id;

        final String url;

        private Channel(String id, String url) {
            this.id = id;
            this.url = url;
        }
    }
}
