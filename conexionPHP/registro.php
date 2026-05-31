<?php
mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);

$servidor    = "localhost";
$usuario_db  = "root";
$password_db = "";
$db_nombre   = "licoreria";

try {
    $link = mysqli_connect($servidor, $usuario_db, $password_db, $db_nombre);
    mysqli_set_charset($link, "utf8");

    if ($_SERVER["REQUEST_METHOD"] == "POST") {

        $nombre = trim($_POST['NombreCompleto'] ?? '');
        $clave  = trim($_POST['clave']          ?? '');
        $cargo  = trim($_POST['cargo']          ?? '');
        $accion = trim($_POST['accion']         ?? 'registrar'); 

        if ($nombre === '' || $clave === '' || $cargo === '') {
            throw new Exception("Error: Todos los campos son obligatorios.");
        }

        $nombre_safe = mysqli_real_escape_string($link, $nombre);
        $clave_safe  = mysqli_real_escape_string($link, $clave);
        $cargo_safe  = mysqli_real_escape_string($link, $cargo);


        if ($accion === 'registrar') {

            if (!preg_match("/^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\s]+$/", $nombre)) {
                throw new Exception("Error: El nombre solo puede contener letras, tildes y espacios.");
            }

            $sql_buscar_duplicado = "SELECT id FROM usuario WHERE nombre = '$nombre_safe' LIMIT 1";
            $resultado_duplicado = mysqli_query($link, $sql_buscar_duplicado);

            if (mysqli_num_rows($resultado_duplicado) > 0) {
                throw new Exception("El nombre de usuario ya se encuentra registrado. Intente con otro.");
            }

            mysqli_begin_transaction($link);

            $sql_usuario = "INSERT INTO usuario (nombre, clave, cargo) VALUES ('$nombre_safe', '$clave_safe', '$cargo_safe')";
            mysqli_query($link, $sql_usuario);

            $id_usuario_generado = mysqli_insert_id($link);

            if ($cargo_safe === 'Gerente') {
                $sql_rol = "INSERT INTO gerente (id_usuario_aux) VALUES ($id_usuario_generado)";
            } else if ($cargo_safe === 'Empleado') {
                $sql_rol = "INSERT INTO empleado (id_usuario_aux) VALUES ($id_usuario_generado)";
            }
            mysqli_query($link, $sql_rol);

            mysqli_commit($link);
            echo "Registro exitoso";

        } 
        else if ($accion === 'eliminar') {

            $sql_buscar = "SELECT id FROM usuario 
                           WHERE nombre = '$nombre_safe' 
                             AND clave  = '$clave_safe' 
                             AND cargo  = '$cargo_safe' 
                           LIMIT 1";
            $resultado = mysqli_query($link, $sql_buscar);

            if ($resultado && mysqli_num_rows($resultado) > 0) {
                $fila = mysqli_fetch_assoc($resultado);
                $id_usuario = $fila['id'];

                mysqli_begin_transaction($link);

                if ($cargo_safe === 'Gerente') {
                    $sql_borrar_rol = "DELETE FROM gerente WHERE id_usuario_aux = $id_usuario";
                } else if ($cargo_safe === 'Empleado') {
                    $sql_borrar_rol = "DELETE FROM empleado WHERE id_usuario_aux = $id_usuario";
                }
                mysqli_query($link, $sql_borrar_rol);

                $sql_borrar_usuario = "DELETE FROM usuario WHERE id = $id_usuario";
                mysqli_query($link, $sql_borrar_usuario);

                mysqli_commit($link);
                echo "Cuenta eliminada con éxito";
            } else {
                throw new Exception("Error: Los datos no coinciden con ninguna cuenta existente.");
            }
        }
    }

} catch (mysqli_sql_exception $e) {
    if (isset($link) && $link) {
        mysqli_rollback($link);
    }
    echo "Error en la base de datos: " . $e->getMessage();
} catch (Exception $e) {
    echo $e->getMessage();
} finally {
    if (isset($link) && $link) {
        mysqli_close($link);
    }
}
?>