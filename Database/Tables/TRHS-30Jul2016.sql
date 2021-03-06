USE [TRHS]
GO
/****** Object:  Table [dbo].[tTravelRequestStatus]    Script Date: 07/30/2016 18:42:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[tTravelRequestStatus]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[tTravelRequestStatus](
	[TravelRequestStatusId] [uniqueidentifier] NOT NULL,
	[TravelRequestName] [varchar](20) NOT NULL,
	[ActionDate] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[TravelRequestStatusId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[TravelRequestName] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tDesignation]    Script Date: 07/30/2016 18:42:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[tDesignation]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[tDesignation](
	[DesignationId] [uniqueidentifier] NOT NULL,
	[DesignationName] [varchar](200) NOT NULL,
	[ActionDate] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[DesignationId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[DesignationName] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tDepartments]    Script Date: 07/30/2016 18:42:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[tDepartments]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[tDepartments](
	[DepartmentId] [uniqueidentifier] NOT NULL,
	[DepartmentName] [varchar](200) NOT NULL,
	[ActionDate] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[DepartmentId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[DepartmentName] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tCompanyDetails]    Script Date: 07/30/2016 18:42:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[tCompanyDetails]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[tCompanyDetails](
	[CompanyId] [uniqueidentifier] NOT NULL,
	[CompanyName] [varchar](200) NOT NULL,
	[CompanyUrl] [varchar](200) NULL,
	[CompanyAddress] [varchar](2000) NOT NULL,
	[CompanyPhone] [varchar](30) NULL,
	[CompanyFax] [varchar](30) NULL,
	[ActionDate] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[CompanyId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[CompanyName] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tTravelModes]    Script Date: 07/30/2016 18:42:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[tTravelModes]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[tTravelModes](
	[TravelModeId] [uniqueidentifier] NOT NULL,
	[TravelModeName] [varchar](30) NOT NULL,
	[ActionDate] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[TravelModeId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[TravelModeName] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tTravelLog]    Script Date: 07/30/2016 18:42:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[tTravelLog]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[tTravelLog](
	[TravelLogId] [uniqueidentifier] NOT NULL,
	[LogMessage] [varchar](max) NOT NULL,
	[DetailException] [varchar](max) NOT NULL,
	[InnerException] [varchar](max) NOT NULL,
	[ClassName] [varchar](30) NOT NULL,
	[MethodName] [varchar](50) NOT NULL,
	[ActionDate] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[TravelLogId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tEmployeeRoles]    Script Date: 07/30/2016 18:42:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[tEmployeeRoles]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[tEmployeeRoles](
	[EmployeeRoleId] [uniqueidentifier] NOT NULL,
	[EmployeeRoleName] [varchar](20) NOT NULL,
	[ActionDate] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[EmployeeRoleId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[EmployeeRoleName] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tEmployeeDetails]    Script Date: 07/30/2016 18:42:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[tEmployeeDetails]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[tEmployeeDetails](
	[EmployeeDetailsId] [uniqueidentifier] NOT NULL,
	[EmployeeId] [varchar](200) NOT NULL,
	[EmployeeName] [varchar](200) NOT NULL,
	[EmployeeShortName] [varchar](30) NOT NULL,
	[EmailId] [varchar](30) NOT NULL,
	[MobileNumber] [varchar](20) NOT NULL,
	[LandlineNumber] [varchar](20) NULL,
	[ExtnNumber] [varchar](20) NULL,
	[DateOfBirth] [datetime] NOT NULL,
	[DesignationId] [uniqueidentifier] NOT NULL,
	[DepartmentId] [uniqueidentifier] NOT NULL,
	[ActionDate] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[EmployeeDetailsId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[MobileNumber] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[EmployeeId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[EmailId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tLogin]    Script Date: 07/30/2016 18:42:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[tLogin]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[tLogin](
	[LoginId] [uniqueidentifier] NOT NULL,
	[UserName] [varchar](20) NOT NULL,
	[LoginPassword] [varchar](30) NOT NULL,
	[EmployeeDetailsId] [uniqueidentifier] NOT NULL,
	[LastLoginDate] [datetime] NOT NULL,
	[IsRandomPwd] [bit] NOT NULL,
	[IsActive] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[LoginId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[UserName] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tEmployeeTravelModes]    Script Date: 07/30/2016 18:42:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[tEmployeeTravelModes]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[tEmployeeTravelModes](
	[EmployeeTravelModeId] [uniqueidentifier] NOT NULL,
	[EmployeeDetailsId] [uniqueidentifier] NOT NULL,
	[TravelModeId] [uniqueidentifier] NOT NULL,
	[IsActive] [bit] NOT NULL,
	[ActionDate] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[EmployeeTravelModeId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[tEmployeeAccessRights]    Script Date: 07/30/2016 18:42:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[tEmployeeAccessRights]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[tEmployeeAccessRights](
	[EmployeeAccessRightsId] [uniqueidentifier] NOT NULL,
	[EmployeeDetailsId] [uniqueidentifier] NOT NULL,
	[EmployeeRoleId] [uniqueidentifier] NOT NULL,
	[ActionDate] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[EmployeeAccessRightsId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[tTravelRequestMaster]    Script Date: 07/30/2016 18:42:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[tTravelRequestMaster]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[tTravelRequestMaster](
	[TravelRequestMasterId] [uniqueidentifier] NOT NULL,
	[RequestNumber] [int] IDENTITY(1,1) NOT NULL,
	[EmployeeDetailsId] [uniqueidentifier] NOT NULL,
	[AttachmentPath] [varchar](100) NULL,
	[TravelRequestStatusId] [uniqueidentifier] NOT NULL,
	[ActionDate] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[TravelRequestMasterId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[RequestNumber] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tTravelRequestVersion]    Script Date: 07/30/2016 18:42:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[tTravelRequestVersion]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[tTravelRequestVersion](
	[TravelRequestVersionId] [uniqueidentifier] NOT NULL,
	[TravelRequestMasterId] [uniqueidentifier] NOT NULL,
	[TravelApproverId] [uniqueidentifier] NOT NULL,
	[ApproverOrder] [int] NOT NULL,
	[ActionDate] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[TravelRequestVersionId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[tTravelRequestComments]    Script Date: 07/30/2016 18:42:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[tTravelRequestComments]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[tTravelRequestComments](
	[TravelRequestCommentsId] [uniqueidentifier] NOT NULL,
	[TravelRequestVersionId] [uniqueidentifier] NOT NULL,
	[SenderId] [uniqueidentifier] NOT NULL,
	[ReceiverId] [uniqueidentifier] NOT NULL,
	[Comments] [varchar](3000) NOT NULL,
	[ActionDate] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[TravelRequestCommentsId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Default [DF__tCompanyD__Actio__52593CB8]    Script Date: 07/30/2016 18:42:40 ******/
IF Not EXISTS (SELECT * FROM sys.default_constraints WHERE object_id = OBJECT_ID(N'[dbo].[DF__tCompanyD__Actio__52593CB8]') AND parent_object_id = OBJECT_ID(N'[dbo].[tCompanyDetails]'))
Begin
IF NOT EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[DF__tCompanyD__Actio__52593CB8]') AND type = 'D')
BEGIN
ALTER TABLE [dbo].[tCompanyDetails] ADD  DEFAULT (getdate()) FOR [ActionDate]
END


End
GO
/****** Object:  Default [DF__tDepartme__Actio__534D60F1]    Script Date: 07/30/2016 18:42:40 ******/
IF Not EXISTS (SELECT * FROM sys.default_constraints WHERE object_id = OBJECT_ID(N'[dbo].[DF__tDepartme__Actio__534D60F1]') AND parent_object_id = OBJECT_ID(N'[dbo].[tDepartments]'))
Begin
IF NOT EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[DF__tDepartme__Actio__534D60F1]') AND type = 'D')
BEGIN
ALTER TABLE [dbo].[tDepartments] ADD  DEFAULT (getdate()) FOR [ActionDate]
END


End
GO
/****** Object:  Default [DF__tDesignat__Actio__5441852A]    Script Date: 07/30/2016 18:42:40 ******/
IF Not EXISTS (SELECT * FROM sys.default_constraints WHERE object_id = OBJECT_ID(N'[dbo].[DF__tDesignat__Actio__5441852A]') AND parent_object_id = OBJECT_ID(N'[dbo].[tDesignation]'))
Begin
IF NOT EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[DF__tDesignat__Actio__5441852A]') AND type = 'D')
BEGIN
ALTER TABLE [dbo].[tDesignation] ADD  DEFAULT (getdate()) FOR [ActionDate]
END


End
GO
/****** Object:  Default [DF__tEmployee__Actio__5535A963]    Script Date: 07/30/2016 18:42:40 ******/
IF Not EXISTS (SELECT * FROM sys.default_constraints WHERE object_id = OBJECT_ID(N'[dbo].[DF__tEmployee__Actio__5535A963]') AND parent_object_id = OBJECT_ID(N'[dbo].[tEmployeeAccessRights]'))
Begin
IF NOT EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[DF__tEmployee__Actio__5535A963]') AND type = 'D')
BEGIN
ALTER TABLE [dbo].[tEmployeeAccessRights] ADD  DEFAULT (getdate()) FOR [ActionDate]
END


End
GO
/****** Object:  Default [DF__tEmployee__Actio__5629CD9C]    Script Date: 07/30/2016 18:42:40 ******/
IF Not EXISTS (SELECT * FROM sys.default_constraints WHERE object_id = OBJECT_ID(N'[dbo].[DF__tEmployee__Actio__5629CD9C]') AND parent_object_id = OBJECT_ID(N'[dbo].[tEmployeeDetails]'))
Begin
IF NOT EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[DF__tEmployee__Actio__5629CD9C]') AND type = 'D')
BEGIN
ALTER TABLE [dbo].[tEmployeeDetails] ADD  DEFAULT (getdate()) FOR [ActionDate]
END


End
GO
/****** Object:  Default [DF__tEmployee__Actio__571DF1D5]    Script Date: 07/30/2016 18:42:40 ******/
IF Not EXISTS (SELECT * FROM sys.default_constraints WHERE object_id = OBJECT_ID(N'[dbo].[DF__tEmployee__Actio__571DF1D5]') AND parent_object_id = OBJECT_ID(N'[dbo].[tEmployeeRoles]'))
Begin
IF NOT EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[DF__tEmployee__Actio__571DF1D5]') AND type = 'D')
BEGIN
ALTER TABLE [dbo].[tEmployeeRoles] ADD  DEFAULT (getdate()) FOR [ActionDate]
END


End
GO
/****** Object:  Default [DF__tEmployee__IsAct__5812160E]    Script Date: 07/30/2016 18:42:40 ******/
IF Not EXISTS (SELECT * FROM sys.default_constraints WHERE object_id = OBJECT_ID(N'[dbo].[DF__tEmployee__IsAct__5812160E]') AND parent_object_id = OBJECT_ID(N'[dbo].[tEmployeeTravelModes]'))
Begin
IF NOT EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[DF__tEmployee__IsAct__5812160E]') AND type = 'D')
BEGIN
ALTER TABLE [dbo].[tEmployeeTravelModes] ADD  DEFAULT ((1)) FOR [IsActive]
END


End
GO
/****** Object:  Default [DF__tEmployee__Actio__59063A47]    Script Date: 07/30/2016 18:42:40 ******/
IF Not EXISTS (SELECT * FROM sys.default_constraints WHERE object_id = OBJECT_ID(N'[dbo].[DF__tEmployee__Actio__59063A47]') AND parent_object_id = OBJECT_ID(N'[dbo].[tEmployeeTravelModes]'))
Begin
IF NOT EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[DF__tEmployee__Actio__59063A47]') AND type = 'D')
BEGIN
ALTER TABLE [dbo].[tEmployeeTravelModes] ADD  DEFAULT (getdate()) FOR [ActionDate]
END


End
GO
/****** Object:  Default [DF__tLogin__LastLogi__59FA5E80]    Script Date: 07/30/2016 18:42:40 ******/
IF Not EXISTS (SELECT * FROM sys.default_constraints WHERE object_id = OBJECT_ID(N'[dbo].[DF__tLogin__LastLogi__59FA5E80]') AND parent_object_id = OBJECT_ID(N'[dbo].[tLogin]'))
Begin
IF NOT EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[DF__tLogin__LastLogi__59FA5E80]') AND type = 'D')
BEGIN
ALTER TABLE [dbo].[tLogin] ADD  DEFAULT (getdate()) FOR [LastLoginDate]
END


End
GO
/****** Object:  Default [DF__tLogin__IsRandom__5AEE82B9]    Script Date: 07/30/2016 18:42:40 ******/
IF Not EXISTS (SELECT * FROM sys.default_constraints WHERE object_id = OBJECT_ID(N'[dbo].[DF__tLogin__IsRandom__5AEE82B9]') AND parent_object_id = OBJECT_ID(N'[dbo].[tLogin]'))
Begin
IF NOT EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[DF__tLogin__IsRandom__5AEE82B9]') AND type = 'D')
BEGIN
ALTER TABLE [dbo].[tLogin] ADD  DEFAULT ((1)) FOR [IsRandomPwd]
END


End
GO
/****** Object:  Default [DF__tLogin__IsActive__5BE2A6F2]    Script Date: 07/30/2016 18:42:40 ******/
IF Not EXISTS (SELECT * FROM sys.default_constraints WHERE object_id = OBJECT_ID(N'[dbo].[DF__tLogin__IsActive__5BE2A6F2]') AND parent_object_id = OBJECT_ID(N'[dbo].[tLogin]'))
Begin
IF NOT EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[DF__tLogin__IsActive__5BE2A6F2]') AND type = 'D')
BEGIN
ALTER TABLE [dbo].[tLogin] ADD  DEFAULT ((1)) FOR [IsActive]
END


End
GO
/****** Object:  Default [DF__tTravelLo__Actio__5CD6CB2B]    Script Date: 07/30/2016 18:42:40 ******/
IF Not EXISTS (SELECT * FROM sys.default_constraints WHERE object_id = OBJECT_ID(N'[dbo].[DF__tTravelLo__Actio__5CD6CB2B]') AND parent_object_id = OBJECT_ID(N'[dbo].[tTravelLog]'))
Begin
IF NOT EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[DF__tTravelLo__Actio__5CD6CB2B]') AND type = 'D')
BEGIN
ALTER TABLE [dbo].[tTravelLog] ADD  DEFAULT (getdate()) FOR [ActionDate]
END


End
GO
/****** Object:  Default [DF__tTravelMo__Actio__5DCAEF64]    Script Date: 07/30/2016 18:42:40 ******/
IF Not EXISTS (SELECT * FROM sys.default_constraints WHERE object_id = OBJECT_ID(N'[dbo].[DF__tTravelMo__Actio__5DCAEF64]') AND parent_object_id = OBJECT_ID(N'[dbo].[tTravelModes]'))
Begin
IF NOT EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[DF__tTravelMo__Actio__5DCAEF64]') AND type = 'D')
BEGIN
ALTER TABLE [dbo].[tTravelModes] ADD  DEFAULT (getdate()) FOR [ActionDate]
END


End
GO
/****** Object:  Default [DF__tTravelRe__Actio__5EBF139D]    Script Date: 07/30/2016 18:42:40 ******/
IF Not EXISTS (SELECT * FROM sys.default_constraints WHERE object_id = OBJECT_ID(N'[dbo].[DF__tTravelRe__Actio__5EBF139D]') AND parent_object_id = OBJECT_ID(N'[dbo].[tTravelRequestComments]'))
Begin
IF NOT EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[DF__tTravelRe__Actio__5EBF139D]') AND type = 'D')
BEGIN
ALTER TABLE [dbo].[tTravelRequestComments] ADD  DEFAULT (getdate()) FOR [ActionDate]
END


End
GO
/****** Object:  Default [DF__tTravelRe__Actio__5FB337D6]    Script Date: 07/30/2016 18:42:40 ******/
IF Not EXISTS (SELECT * FROM sys.default_constraints WHERE object_id = OBJECT_ID(N'[dbo].[DF__tTravelRe__Actio__5FB337D6]') AND parent_object_id = OBJECT_ID(N'[dbo].[tTravelRequestMaster]'))
Begin
IF NOT EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[DF__tTravelRe__Actio__5FB337D6]') AND type = 'D')
BEGIN
ALTER TABLE [dbo].[tTravelRequestMaster] ADD  DEFAULT (getdate()) FOR [ActionDate]
END


End
GO
/****** Object:  Default [DF__tTravelRe__Actio__60A75C0F]    Script Date: 07/30/2016 18:42:40 ******/
IF Not EXISTS (SELECT * FROM sys.default_constraints WHERE object_id = OBJECT_ID(N'[dbo].[DF__tTravelRe__Actio__60A75C0F]') AND parent_object_id = OBJECT_ID(N'[dbo].[tTravelRequestStatus]'))
Begin
IF NOT EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[DF__tTravelRe__Actio__60A75C0F]') AND type = 'D')
BEGIN
ALTER TABLE [dbo].[tTravelRequestStatus] ADD  DEFAULT (getdate()) FOR [ActionDate]
END


End
GO
/****** Object:  Default [DF__tTravelRe__Actio__619B8048]    Script Date: 07/30/2016 18:42:40 ******/
IF Not EXISTS (SELECT * FROM sys.default_constraints WHERE object_id = OBJECT_ID(N'[dbo].[DF__tTravelRe__Actio__619B8048]') AND parent_object_id = OBJECT_ID(N'[dbo].[tTravelRequestVersion]'))
Begin
IF NOT EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[DF__tTravelRe__Actio__619B8048]') AND type = 'D')
BEGIN
ALTER TABLE [dbo].[tTravelRequestVersion] ADD  DEFAULT (getdate()) FOR [ActionDate]
END


End
GO
/****** Object:  ForeignKey [FK__tEmployee__Emplo__628FA481]    Script Date: 07/30/2016 18:42:40 ******/
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK__tEmployee__Emplo__628FA481]') AND parent_object_id = OBJECT_ID(N'[dbo].[tEmployeeAccessRights]'))
ALTER TABLE [dbo].[tEmployeeAccessRights]  WITH CHECK ADD FOREIGN KEY([EmployeeDetailsId])
REFERENCES [dbo].[tEmployeeDetails] ([EmployeeDetailsId])
GO
/****** Object:  ForeignKey [FK__tEmployee__Emplo__6383C8BA]    Script Date: 07/30/2016 18:42:40 ******/
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK__tEmployee__Emplo__6383C8BA]') AND parent_object_id = OBJECT_ID(N'[dbo].[tEmployeeAccessRights]'))
ALTER TABLE [dbo].[tEmployeeAccessRights]  WITH CHECK ADD FOREIGN KEY([EmployeeRoleId])
REFERENCES [dbo].[tEmployeeRoles] ([EmployeeRoleId])
GO
/****** Object:  ForeignKey [FK__tEmployee__Depar__6477ECF3]    Script Date: 07/30/2016 18:42:40 ******/
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK__tEmployee__Depar__6477ECF3]') AND parent_object_id = OBJECT_ID(N'[dbo].[tEmployeeDetails]'))
ALTER TABLE [dbo].[tEmployeeDetails]  WITH CHECK ADD FOREIGN KEY([DepartmentId])
REFERENCES [dbo].[tDepartments] ([DepartmentId])
GO
/****** Object:  ForeignKey [FK__tEmployee__Desig__656C112C]    Script Date: 07/30/2016 18:42:40 ******/
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK__tEmployee__Desig__656C112C]') AND parent_object_id = OBJECT_ID(N'[dbo].[tEmployeeDetails]'))
ALTER TABLE [dbo].[tEmployeeDetails]  WITH CHECK ADD FOREIGN KEY([DesignationId])
REFERENCES [dbo].[tDesignation] ([DesignationId])
GO
/****** Object:  ForeignKey [FK__tEmployee__Emplo__66603565]    Script Date: 07/30/2016 18:42:40 ******/
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK__tEmployee__Emplo__66603565]') AND parent_object_id = OBJECT_ID(N'[dbo].[tEmployeeTravelModes]'))
ALTER TABLE [dbo].[tEmployeeTravelModes]  WITH CHECK ADD FOREIGN KEY([EmployeeDetailsId])
REFERENCES [dbo].[tEmployeeDetails] ([EmployeeDetailsId])
GO
/****** Object:  ForeignKey [FK__tEmployee__Trave__6754599E]    Script Date: 07/30/2016 18:42:40 ******/
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK__tEmployee__Trave__6754599E]') AND parent_object_id = OBJECT_ID(N'[dbo].[tEmployeeTravelModes]'))
ALTER TABLE [dbo].[tEmployeeTravelModes]  WITH CHECK ADD FOREIGN KEY([TravelModeId])
REFERENCES [dbo].[tTravelModes] ([TravelModeId])
GO
/****** Object:  ForeignKey [FK__tLogin__Employee__68487DD7]    Script Date: 07/30/2016 18:42:40 ******/
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK__tLogin__Employee__68487DD7]') AND parent_object_id = OBJECT_ID(N'[dbo].[tLogin]'))
ALTER TABLE [dbo].[tLogin]  WITH CHECK ADD FOREIGN KEY([EmployeeDetailsId])
REFERENCES [dbo].[tEmployeeDetails] ([EmployeeDetailsId])
GO
/****** Object:  ForeignKey [FK__tTravelRe__Recei__693CA210]    Script Date: 07/30/2016 18:42:40 ******/
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK__tTravelRe__Recei__693CA210]') AND parent_object_id = OBJECT_ID(N'[dbo].[tTravelRequestComments]'))
ALTER TABLE [dbo].[tTravelRequestComments]  WITH CHECK ADD FOREIGN KEY([ReceiverId])
REFERENCES [dbo].[tEmployeeDetails] ([EmployeeDetailsId])
GO
/****** Object:  ForeignKey [FK__tTravelRe__Sende__6A30C649]    Script Date: 07/30/2016 18:42:40 ******/
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK__tTravelRe__Sende__6A30C649]') AND parent_object_id = OBJECT_ID(N'[dbo].[tTravelRequestComments]'))
ALTER TABLE [dbo].[tTravelRequestComments]  WITH CHECK ADD FOREIGN KEY([SenderId])
REFERENCES [dbo].[tEmployeeDetails] ([EmployeeDetailsId])
GO
/****** Object:  ForeignKey [FK__tTravelRe__Trave__6B24EA82]    Script Date: 07/30/2016 18:42:40 ******/
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK__tTravelRe__Trave__6B24EA82]') AND parent_object_id = OBJECT_ID(N'[dbo].[tTravelRequestComments]'))
ALTER TABLE [dbo].[tTravelRequestComments]  WITH CHECK ADD FOREIGN KEY([TravelRequestVersionId])
REFERENCES [dbo].[tTravelRequestVersion] ([TravelRequestVersionId])
GO
/****** Object:  ForeignKey [FK__tTravelRe__Emplo__6C190EBB]    Script Date: 07/30/2016 18:42:40 ******/
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK__tTravelRe__Emplo__6C190EBB]') AND parent_object_id = OBJECT_ID(N'[dbo].[tTravelRequestMaster]'))
ALTER TABLE [dbo].[tTravelRequestMaster]  WITH CHECK ADD FOREIGN KEY([EmployeeDetailsId])
REFERENCES [dbo].[tEmployeeDetails] ([EmployeeDetailsId])
GO
/****** Object:  ForeignKey [FK__tTravelRe__Trave__6D0D32F4]    Script Date: 07/30/2016 18:42:40 ******/
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK__tTravelRe__Trave__6D0D32F4]') AND parent_object_id = OBJECT_ID(N'[dbo].[tTravelRequestMaster]'))
ALTER TABLE [dbo].[tTravelRequestMaster]  WITH CHECK ADD FOREIGN KEY([TravelRequestStatusId])
REFERENCES [dbo].[tTravelRequestStatus] ([TravelRequestStatusId])
GO
/****** Object:  ForeignKey [FK__tTravelRe__Trave__6E01572D]    Script Date: 07/30/2016 18:42:40 ******/
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK__tTravelRe__Trave__6E01572D]') AND parent_object_id = OBJECT_ID(N'[dbo].[tTravelRequestVersion]'))
ALTER TABLE [dbo].[tTravelRequestVersion]  WITH CHECK ADD FOREIGN KEY([TravelRequestMasterId])
REFERENCES [dbo].[tTravelRequestMaster] ([TravelRequestMasterId])
GO
/****** Object:  ForeignKey [FK__tTravelRe__Trave__6EF57B66]    Script Date: 07/30/2016 18:42:40 ******/
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK__tTravelRe__Trave__6EF57B66]') AND parent_object_id = OBJECT_ID(N'[dbo].[tTravelRequestVersion]'))
ALTER TABLE [dbo].[tTravelRequestVersion]  WITH CHECK ADD FOREIGN KEY([TravelApproverId])
REFERENCES [dbo].[tEmployeeDetails] ([EmployeeDetailsId])
GO
