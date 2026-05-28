<?php
// ── Conexión ─────────────────────────────────────────────────────────────────
$servidor    = "localhost";
$usuario_db  = "root";
$password_db = "";
$db_nombre   = "licoreria";

$link = mysqli_connect($servidor, $usuario_db, $password_db, $db_nombre);
mysqli_set_charset($link, "utf8");

if (!$link) {
    echo "ERROR_CONEXION";
    exit;
}

// ── Solo procesa POST ─────────────────────────────────────────────────────────
if ($_SERVER["REQUEST_METHOD"] == "POST") {

    // Logeo.java envía: nombre | clave | cargo
    $nombre = mysqli_real_escape_string($link, trim($_POST['nombre'] ?? ''));
    $clave  = mysqli_real_escape_string($link, trim($_POST['clave']  ?? ''));
    $cargo  = mysqli_real_escape_string($link, trim($_POST['cargo']  ?? ''));

    if ($nombre === '' || $clave === '' || $cargo === '') {
        echo "CAMPOS_VACIOS";
        mysqli_close($link);
        exit;
    }

    // SELECT contra la columna 'clave' — solo lectura, nunca modifica la BD
    $sql = "SELECT cargo FROM usuario
            WHERE nombre = '$nombre'
              AND clave  = '$clave'
              AND cargo  = '$cargo'
            LIMIT 1";

    $resultado = mysqli_query($link, $sql);

    if ($resultado && mysqli_num_rows($resultado) > 0) {
        $fila = mysqli_fetch_assoc($resultado);
        echo $fila['cargo'];          // Devuelve "Gerente" o "Empleado"
    } else {
        echo "CREDENCIALES_INVALIDAS";
    }
}

mysqli_close($link);
?>