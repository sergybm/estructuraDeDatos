class Cliente {
		int productos;
		double tiempoLlegada;

		public Cliente(double tiempoLlegada) {
			this.productos = (int) (Math.random() * (10 - 1) + 1);
			this.tiempoLlegada = tiempoLlegada ;
		}

		public int getProductos() {
			return productos;
		}

		public void setProductos(int productos) {
			this.productos = productos;
		}

		public double getTiempoLlegada() {
			return tiempoLlegada;
		}

		public void setTiempoLlegada(double tiempoLlegada) {
			this.tiempoLlegada = tiempoLlegada;
		}

		@Override
		public String toString() {
			return " [productos=" + productos + ", tiempoLlegada=" + tiempoLlegada + "]";
		}

	}