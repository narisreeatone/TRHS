using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TRHS.SharedComponents;

namespace TRHS.DataAccessComponents
{
    internal class DataAccess
    {
        private static readonly string ExportRegisterDBConnection;

        static DataAccess()
        {
            var configLoader = new ConfigHelper();
            ExportRegisterDBConnection = configLoader.GetConnectionStringFromKey(DatabaseConstants.DBname);
        }

        public static TravelRequestHandlingDataContext GetDataContext()
        {
            return new TravelRequestHandlingDataContext(ExportRegisterDBConnection);
        }

        public static TravelRequestHandlingDataContext GetNoTrackingDataContext()
        {
            var test = new TravelRequestHandlingDataContext(ExportRegisterDBConnection);
            test.ObjectTrackingEnabled = false;
            return test;
        }
    }
}
