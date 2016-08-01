using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(TRHS.Startup))]
namespace TRHS
{
    public partial class Startup {
        public void Configuration(IAppBuilder app) {
            ConfigureAuth(app);
        }
    }
}
