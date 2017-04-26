<!-- author: Leon Dsouza
description: Sidebar, tabs turn active through jquery -->
<!-- -------------------------------------------------------------------------------------------------------- -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <script type="text/javascript">
        function getMonthNumber() {
            var d = new Date();
            var n = d.getMonth() + 1;

            window.location
                    .replace("SubmitMonth?month="
                            + n);
        }
    </script>
    <body class="skin-3 no-skin">

        <div id="sidebar" class="sidebar responsive">
            <script type="text/javascript">
                try {
                    ace.settings.check('sidebar', 'fixed')
                } catch (e) {
                }
            </script>



            <ul class="nav nav-list" id="sidebarli">

                <li class=" " id="FAQ"><a href="/"> <i class="menu-icon
                                                       fa fa-calculator"></i> <span class="menu-text">ARM on SW CSV</span>
                    </a> <b class="arrow"></b></li>
                <li class=" " id="about_us"><a href="sparql"> <i class="menu-icon fa 
                                                                 fa-angle-double-left"></i> <span class="menu-text">Mine SW Data</span>
                        <li class=" " id="Black Book"><a href="https://drive.google.com/open?id=0BzTJ0S4UKParTTQ1UllnRHRBb0gyVFNtNnhvR3RKYWpPM1pJ"> <i class="menu-icon fa 
                                                                                                                                                       fa-book"></i> <span class="menu-text">Black Book</span>
                            </a> <b class="arrow"></b></li>
                        <li class=" " id="Black Book"><a href="https://drive.google.com/open?id=0BzTJ0S4UKParTTQ1UllnRHRBb0gyVFNtNnhvR3RKYWpPM1pJ"> <i class="menu-icon fa 
                                                                                                                                                       fa-code-fork"></i> <span class="menu-text">Source Code</span>
                            </a> <b class="arrow"></b></li>
                        
            </ul>
            <!--  End of nav list-->


            <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
                <i class="ace-icon fa fa-angle-double-left"
                   data-icon1="ace-icon fa fa-angle-double-left"
                   data-icon2="ace-icon fa fa-angle-double-right"></i>
            </div>

            <script type="text/javascript">
                try {
                    ace.settings.check('sidebar', 'collapsed')
                } catch (e) {
                }
            </script>

            <script>
                $(document).ready(function () {
                    var i = window.location.pathname;
                    var j = i.slice(8); //needs to b changed count from first letter of proj to next /

                    $("#Index").addClass("active");
                    if (j != null) {
                        $('#Index').removeClass("active");
                        $("#" + j).addClass("active");
                    }
                })
            </script>


        </div>
        <!-- End of sidebar responsive -->
    </body>
</html>


<%-- <!-- author: Gaurav Shinde
description: Sidebar, tabs turn active through jquery -->
<!-- -------------------------------------------------------------------------------------------------------- -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body class="skin-3 no-skin">

        <div id="sidebar" class="sidebar responsive">
                <script type="text/javascript">
                        try {
                                ace.settings.check('sidebar', 'fixed')
                        } catch (e) {
                        }
                </script>



                <ul class="nav nav-list" id="sidebarli">
                        <li class=" " id="InsertMonth"><a href="InsertMonth"> <i
                                        class="menu-icon fa fa-calendar"></i> <span class="menu-text">
                                                Events </span>
                        </a> <b class="arrow"></b></li>

                        <li class=" " id="searchHome"><a href="searchHome"> <i
                                        class="menu-icon fa fa-search"></i> <span class="menu-text">
                                                Search </span>
                        </a> <b class="arrow"></b></li>

                        <li class=" " id="CompaniesPage"><a href="CompaniesPage"> <i
                                        class="menu-icon fa fa-gears"></i> <span class="menu-text">
                                                Companies </span>
                        </a> <b class="arrow"></b></li>

                        <li class=" " id="Highlights"><a href="Statistics"> <i class="menu-icon
                                                fa fa-bar-chart-o"></i> <span class="menu-text"> Statistics </span>
                        </a> <b class="arrow"></b></li>
                </ul>
                <!--  End of nav list-->


                <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
                        <i class="ace-icon fa fa-angle-double-left"
                                data-icon1="ace-icon fa fa-angle-double-left"
                                data-icon2="ace-icon fa fa-angle-double-right"></i>
                </div>

                <script type="text/javascript">
                        try {
                                ace.settings.check('sidebar', 'collapsed')
                        } catch (e) {
                        }
                </script>

                <script>
                        $(document).ready(function() {
                                var i = window.location.pathname;
                                var j = i.slice(8); //needs to b changed count from first letter of proj to next /

                                $("#Index").addClass("active");
                                if (j != null) {
                                        $('#Index').removeClass("active");
                                        $("#" + j).addClass("active");
                                }
                        })
                </script>


        </div>
        <!-- End of sidebar responsive -->
</body>
</html> --%>