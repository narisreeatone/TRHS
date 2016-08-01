using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TRHS.SharedComponents
{
    public class DatabaseConstants
    {
        public const string ApplicationName = "TRHS";
        public const string DBname = "TRHS";
        public static string LogDBname = "TRHS_LOG";
    }

    public enum LogMessageTypes
    {
        Error = 1,
        Info = 2
    }
}
