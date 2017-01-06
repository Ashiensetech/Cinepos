<div class="navbar-default sidebar" role="navigation">
    <div class="sidebar-nav navbar-collapse">
        <ul class="nav" id="side-menu">
            <li>
                <a href="index.html">Dashboard</a>
            </li>
            <li>
                <a href="#">Admin<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level collapse">
                    <li>
                        <a href="<c:url value="/admin/user/all" />">All admin</a>
                    </li>
                    <li>
                        <a href="<c:url value="/admin/user/create" />">Create admin</a>
                    </li>

                </ul>
            </li>
            <li>
                <a href="#">Screen<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level collapse">
                    <li>
                        <a href="<c:url value="/admin/screen/all" />">All screen</a>
                    </li>
                    <li>
                        <a href="<c:url value="/admin/screen/create" />">Create screen</a>
                    </li>

                </ul>
                <!-- /.nav-second-level -->
            </li>
        </ul>
    </div>
    <!-- /.sidebar-collapse -->
</div>
<!-- /.navbar-static-side -->