using System;
using System.Collections.Generic;
using System.Data.Linq;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TRHS.SharedComponents;

namespace TRHS.DataAccessComponents
{
    internal static class UserCompiledQueries
    {
        public static readonly Func<TravelRequestHandlingDataContext, string, string, UserDetailsDTO>
           FunValidateUser = CompiledQuery.Compile((TravelRequestHandlingDataContext context, string userName, string passWord) =>
               (
               from access in context.tLogins
               join eDetails in context.tEmployeeDetails on access.EmployeeDetailsId equals eDetails.EmployeeDetailsId
               where access.UserName == userName && access.LoginPassword == passWord
               select new UserDetailsDTO
               {
                   UserName = access.UserName,
                   Email = eDetails.EmailId,
                   UserValid = true,
                   LastLoginDateTime = access.LastLoginDate,
                   DateOfBrith = eDetails.DateOfBirth,
                   EmployeeName = eDetails.EmployeeName,
                   IsRandomPassword = access.IsRandomPwd
               }
               ).FirstOrDefault()
           );
    }
}
