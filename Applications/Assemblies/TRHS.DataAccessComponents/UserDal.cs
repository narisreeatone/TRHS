using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using TRHS.SharedComponents;

namespace TRHS.DataAccessComponents
{
    public class UserDal
    {
        public UserDetailsDTO ValidateUser(string UserName, string Password)
        {
            using (var context = DataAccess.GetNoTrackingDataContext())
            {
                return UserCompiledQueries.FunValidateUser(context, UserName, Password);
            }
        }
    }
}
