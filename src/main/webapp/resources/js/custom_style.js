$( document ).ready(function() {  
    //VISTA FARMACIA
        //Obtener número de tablas
        var $rows = ($("[id='searchForm:lista_recetas'] tbody tr")).length;
        //Limpiar nombre de paciente
        if($rows===0){
            $("#nombre-paciente").empty();
        }
        var $hoy = new Date().getTime();
        for (var i = 0, max = $rows; i < max; i++) {
            var $estadoReceta = $("[id='searchForm:lista_recetas:"+i+":estadoReceta']").text();
            var $inicio = formatearFecha($("[id='searchForm:lista_recetas:"+i+":inicio_validez']").text());
            var $fin = formatearFecha($("[id='searchForm:lista_recetas:"+i+":fin_validez']").text());

            if ($hoy >= $inicio && $hoy<$fin && $estadoReceta!=="SERVIDA"){
                $("[id='searchForm:lista_recetas:"+i+":servir-button']").attr("class","btn btn-primary");  
            }else{
                $("[id='searchForm:lista_recetas:"+i+":servir-button']").attr("class","btn disabled");
            }
        }   
        
    //VISTA MÉDICO
   
    var $hoy = new Date(); 
    $("[id='fecha_citas_hoy']").html("Agenda para el: "+ $hoy.getDate() + "/" +($hoy.getMonth()+1) + "/" +$hoy.getFullYear() );
    var $rows = ($("[id='searchForm:lista_citas'] tbody tr")).length;
    for (var i = 0, max = $rows; i < max; i++) {                
        var $estadoCita = $("[id='searchForm:lista_citas:"+i+":estadoCita']").text();       
        if ($estadoCita==="PLANIFICADA"){
            $("[id='searchForm:lista_citas:"+i+":atender-button']").attr("class","btn btn-primary my-btn");  
        }else{           
            $("[id='searchForm:lista_citas:"+i+":atender-button']").attr("class","btn btn-primary disabled my-btn");
        }        
    }

    
   });
   
   
function formatearFecha(fecha){
    var res = fecha.split("/");
    var dia = res[0]; 
    var mes = res[1];
    var ano = res[2];  
    
    return new Date(mes+"/"+dia+"/"+ano);
}