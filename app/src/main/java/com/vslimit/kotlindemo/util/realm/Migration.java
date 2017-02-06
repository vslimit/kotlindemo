package com.vslimit.kotlindemo.util.realm;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * Created by vslimit on 17/1/23.
 */
public class Migration implements RealmMigration {

    private static Migration instance = null;

    static {
        instance = new Migration();
    }

    private Migration() {
    }

    public static Migration getInstance() {
        return instance;
    }

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
        if (oldVersion == 0) {
            oldVersion++;
        }
//        if (oldVersion == 1) {
//            oldVersion++;
//        }
    }
}
