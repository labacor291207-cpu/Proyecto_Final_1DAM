<?php
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

if ($_SERVER["REQUEST_METHOD"] == "POST") {

    $nombre = mysqli_real_escape_string($link, trim($_POST['nombre'] ?? ''));
    $clave  = mysqli_real_escape_string($link, trim($_POST['clave'] ?? ''));
    $cargo  = mysqli_real_escape_string($link, trim($_POST['cargo'] ?? ''));

    if ($nombre === '' || $clave === '' || $cargo === '') {
        echo "CAMPOS_VACIOS";
        mysqli_close($link);
        exit;
    }

    $sql = "SELECT cargo FROM usuario
            WHERE nombre = '$nombre'
              AND clave  = '$clave'
              AND cargo  = '$cargo'
            LIMIT 1";

    $resultado = mysqli_query($link, $sql);

    if ($resultado && mysqli_num_rows($resultado) > 0) {
        $fila = mysqli_fetch_assoc($resultado);
        echo $fila['cargo'];
    } else {
        echo "CREDENCIALES_INVALIDAS";
    }
}

mysqli_close($link);
?>
