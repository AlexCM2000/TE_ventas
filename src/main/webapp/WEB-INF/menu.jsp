<%

    String opcion = request.getParameter("opcion");

%> 
<ul class="nav nav-tabs">
                <li class="nav-item">
                    <a class="nav-link <%= (opcion.equals("productos")?"active":"")%>" href="ProductoController">Productos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%= (opcion.equals("clientes")?"active":"")%>" href="ClienteController">Clientes</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%= (opcion.equals("ventas")?"active":"")%>" href="VentaController">Ventas</a>
                </li>
                  <li class="nav-item">
                    <a class="nav-link <%= (opcion.equals("#")?"active":"")%>" href="Logout" style="color:#FF0000;" >Cerrar Sesion</a>
                </li>
            </ul>