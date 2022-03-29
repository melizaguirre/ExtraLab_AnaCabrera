/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author BAC
 */
public enum TipoCuenta {
    Normal(0.02,500), Planilla(0,200), VIP (0.04,1000);
    public double tasaInteres;
    public double minSaldo;

    private TipoCuenta(double tasaInteres, double minSaldo) {
        this.tasaInteres = tasaInteres;
        this.minSaldo = minSaldo;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public double getMinSaldo() {
        return minSaldo;
    }

    public void setMinSaldo(double minSaldo) {
        this.minSaldo = minSaldo;
    }
    
    
}
