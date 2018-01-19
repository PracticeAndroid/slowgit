package com.miuty.slowgit.provider.network;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.miuty.slowgit.util.FeedsType;

import java.lang.reflect.Type;

public class FeedsTypeDeserializer implements JsonDeserializer<FeedsType> {

    @Override
    public FeedsType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        FeedsType[] feedsTypes = FeedsType.values();
        for (FeedsType feedsType : feedsTypes) {
            if (feedsType.key.equals(json.getAsString())) {
                return feedsType;
            }
        }
        return null;
    }
}
