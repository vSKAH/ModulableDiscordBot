package fr.skah.mdb.utils;

/*
 *  * @Created on jeudi/avril/2021 - 01:02
 *  * @Project ModulableDiscordBot
 *  * @Author Jimmy
 */

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    private final String loggerPrefix;

    public Logger(String loggerPrefix) {
        this.loggerPrefix = loggerPrefix;
    }

    public String prefix() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String date = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        return "[" + loggerPrefix + " " + date + "] ";
    }

    public void info(String info) {
        System.out.println(prefix() + info);
    }

    public void warn(String warn) {
        System.err.println(prefix() + warn);
    }

}
