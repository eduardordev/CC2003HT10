/*
*Universidad del Valle de Guatemala
*Algoritmos y Estructuras de datos
*Eduardo Ramírez Herrera 19946
*Hoja de Trabajo #10
*CLASE CAMINOS CORTOS CON METODO FLOYDWARSHALL
*/

public class shorterPaths{

	public String Floyd(long [][] mAdy){

		int vertices = mAdy.length;
		long adyMatrix[][] = mAdy;

		String caminos[][] = new String[vertices][vertices];
		String atajos[][] = new String[vertices][vertices];
		String caminoRecorrido="", cadena="", caminosPeq="";

		int i, j, k;

		float temp1, temp2, temp3, temp4, corto;

		//Inicializamos las matrices caminos y atajos
		for(i=0; i<vertices; i++){
			for(j=0; j<vertices; j++){
				caminos[i][j]="";
				atajos[i][j]="";
			}
		}

		for(k=0; k<vertices;k++){

			for(i=0; i<vertices;i++){

				for(j=0; j<vertices;j++){

					temp1 = adyMatrix[i][j];
					temp2 = adyMatrix[i][k];
					temp3 = adyMatrix[k][j];
					temp4 = temp2+temp3;
					//Buscar el camino mas corto

					corto = Math.min(temp1,temp4);

					if(temp1 != temp4){
						if(corto == temp4){
							caminoRecorrido="";
							atajos[i][j]=k+"";
							caminos[i][j]=caminosR(i,k,atajos,caminoRecorrido) + (k+1);
						}
					}

					adyMatrix[i][j]=(long)corto;

				}

			}

		}

		//Agregando el camino a cadena

		for(i=0; i<vertices;i++){

			for(j=0; j<vertices;j++){
				cadena = cadena+"["+adyMatrix[i][j]+"]";
			}
			cadena = cadena + "\n";
		}

		for(i=0; i<vertices; i++){
			for(j=0; j<vertices; j++){
				if(adyMatrix[i][j]!=1000000000){
					if(i!=j){
						if(caminos[i][j].equals("")){
							caminosPeq += "De ("+(i+1)+"--->"+(j+1)+") irse por: ("+(i+1)+", "+(j+1)+")\n";
						}else{ 
							caminosPeq += "De ("+(i+1)+"--->"+(j+1)+") irse por: ("+(i+1)+", "+caminos[i][j]+", "+(j+1)+")\n";
						}
					}
				}
			}
		}

		return "La matriz de caminos mas cortos entre los diferentes vertices es: " + cadena +"\nLos diferentes caminos mas cortos entre vertices son: "+caminosPeq
	}


	public String caminosR(int i, int k, String[][] atajos, String caminoRecorrido){
		if(atajos[i][k].equals("")){

			return "";

		}else{

			//Recursividad
			caminoRecorrido += caminosR(i, Integer.parseInt(atajos[i][k].toString()), atajos, caminoRecorrido)+(Integer.parseInt(atajos[i][k].toString())+1+", ");
			return caminoRecorrido;
		}
	}
}