<?php
// ── Conexión ─────────────────────────────────────────────────────────────────
$servidor    = "localhost";
$usuario_db  = "root";
$password_db = "";
$db_nombre   = "licoreria";

$link = mysqli_connect($servidor, $usuario_db, $password_db, $db_nombre);
mysqli_set_charset($link, "utf8");

if (!$link) {
    die("Error de conexión: " . mysqli_connect_error());
}

// ── Solo procesa POST ─────────────────────────────────────────────────────────
if ($_SERVER["REQUEST_METHOD"] == "POST") {

    // Recibe los tres campos del formulario index.html
    $nombre = mysqli_real_escape_string($link, trim($_POST['NombreCompleto'] ?? ''));
    $clave  = mysqli_real_escape_string($link, trim($_POST['clave']          ?? ''));
    $cargo  = mysqli_real_escape_string($link, trim($_POST['cargo']          ?? 'Empleado'));

    if ($nombre === '' || $clave === '' || $cargo === '') {
        echo "Error: todos los campos son obligatorios";
        mysqli_close($link);
        exit;
    }

    // INSERT usando la columna 'clave' de la tabla usuario
    $sql = "INSERT INTO usuario (nombre, clave, cargo) VALUES ('$nombre', '$clave', '$cargo')";

    if (mysqli_query($link, $sql)) {
        echo "Registro exitoso";
    } else {
        echo "Error: " . mysqli_error($link);
    }
}

mysqli_close($link);
?>