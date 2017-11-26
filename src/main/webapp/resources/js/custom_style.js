$( document ).ready(function() {    
        var $rows = ($("[id='searchForm:lista_recetas'] tbody tr")).length;
        var $hoy = new Date().getTime();
        for (var i = 0, max = $rows; i < max; i++) {
            var $inicio = formatearFecha($("[id='searchForm:lista_recetas:"+i+":inicio_validez']").text());
            var $fin = formatearFecha($("[id='searchForm:lista_recetas:"+i+":fin_validez']").text());

            if($hoy >= $inicio && $hoy<$fin){
                $("[id='searchForm:lista_recetas:"+i+":servir-icon']").html('<span><i id="searchForm:lista_recetas:0:j_idt41" class="fa fa-check"></i></span>');  
            }else{
                $("[id='searchForm:lista_recetas:"+i+":servir-icon']").html('<span><i id="searchForm:lista_recetas:0:j_idt41" class="disabled fa fa-close"></i></span>');  
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