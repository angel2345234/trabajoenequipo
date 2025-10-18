package com.academico.servicio;
import java.math.BigDecimal; import java.util.List;
public class ControlAcademicoService {
    public boolean validarNota(BigDecimal n){ return n!=null && n.compareTo(BigDecimal.ZERO)>=0 && n.compareTo(new BigDecimal("20.00"))<=0; }
    public BigDecimal promedio(List<BigDecimal> notas){
        if(notas==null||notas.isEmpty()) return BigDecimal.ZERO;
        BigDecimal s=BigDecimal.ZERO; for(BigDecimal n:notas) s=s.add(n==null?BigDecimal.ZERO:n);
        return s.divide(new BigDecimal(notas.size()),2,java.math.RoundingMode.HALF_UP);
    }
}
