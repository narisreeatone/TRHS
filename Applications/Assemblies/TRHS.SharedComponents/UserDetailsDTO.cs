using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TRHS.SharedComponents
{
    public class UserDetailsDTO
    {
        public string UserName { get; set; }
        public string Email { get; set; }
        public bool UserValid { get; set; }
        public DateTime? LastLoginDateTime { get; set; }
        public DateTime? DateOfBrith { get; set; }
        public string EmployeeName { get; set; }
        public bool IsRandomPassword { get; set; }
    }
}
