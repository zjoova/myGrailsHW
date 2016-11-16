package com.ms.memsource.util

import liquibase.Liquibase
import liquibase.database.Database
import liquibase.changelog.ChangeSet.ExecType
import org.apache.log4j.Logger

class MigrationCallbacks {
    private List unrun_changesets = []
    Logger log = Logger.getLogger(this.class)

    void beforeStartMigration(Database Database) {
        log.info 'DB migration START'
    }

    void onStartMigration(Database database, Liquibase liquibase, String changelogName) {
        liquibase.listUnrunChangeSets().each {
            unrun_changesets.add(it.getId())
        }

    }

    void afterMigrations(Database Database) {
        List runchsets = Database.getRanChangeSetList()
        runchsets.each {
            String log_message = 'DB migration changeset: '+ it.getId() + ' ' + it.getExecType()
            if (unrun_changesets.contains(it.getId()))
                if (it.getExecType() != ExecType.EXECUTED)
                    log.warn log_message
                else
                    log.debug log_message
        }
        log.info 'DB migration END'
    }

}
