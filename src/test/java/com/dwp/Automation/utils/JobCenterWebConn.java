package com.dwp.Automation.utils;

public class JobCenterWebConn extends AbstractWebconn {


    /** The properties resource */
        private static final String PROPERTIES = "/jobCenter.properties";

        /** The instance of this class */
        private static JobCenterWebConn connector;

        /**
         * Construct this WebConnector.<p>
         *
         * Singleton so constructor is private, see {@link #getInstance()}.<p>
         */
        public JobCenterWebConn()
        {
            super(PROPERTIES);
        }

        /**
         * Static accessor to get this singleton instance.<p>
         *
         */
        public static JobCenterWebConn getInstance()
        {

            connector = new JobCenterWebConn();


            return connector;
        } //getInstance

    }

