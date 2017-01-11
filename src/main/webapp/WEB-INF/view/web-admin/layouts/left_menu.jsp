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
                <a href="/admin/circuit/index">Circuit</a>
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
            <li>
                <a href="#">Distributor<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level collapse">
                    <li>
                        <a href="/admin/distributor/all">All Distributor</a>
                    </li>
                    <li>
                        <a href="/admin/distributor/create">Create Distributor</a>
                    </li>

                </ul>
                <!-- /.nav-second-level -->
            </li>
            <li>
                <a href="#">Terminal<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level collapse">
                    <li>
                        <a href="/admin/terminal/all">All Terminal</a>
                    </li>
                    <li>
                        <a href="/admin/terminal/create">Create Terminal</a>
                    </li>

                </ul>
                <!-- /.nav-second-level -->
            </li>
            <li>
                <a href="#">Seat Type<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level collapse">
                    <li>
                        <a href="/admin/seat-type/all">All Seat Type</a>
                    </li>
                    <li>
                        <a href="/admin/seat-type/create">Create Seat Type</a>
                    </li>

                </ul>
                <!-- /.nav-second-level -->
            </li>
        </ul>
    </div>
    <!-- /.sidebar-collapse -->
</div>
<!-- /.navbar-static-side -->