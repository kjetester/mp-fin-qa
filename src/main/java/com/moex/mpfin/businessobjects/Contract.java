package com.moex.mpfin.businessobjects;

public class Contract {

	private static String contractId;

	public static void setContractId(String contractId) {
		Contract.contractId = contractId;
	}

	public String getContractId() {
		return contractId;
	}

	public enum Status {
		NEW("Новый"),
		WAITING_FOR_BANK_APPROVAL("На согласовании с банком"),
		WAITING_FOR_FUNDS("Ожидание поступления средств"),
		WAITING_FOR_FUNDS_IN_BANK("Ожидание поступления средств в банк"),
		ACTIVE("Действует"),
		ACTIVE_PROLONGED("Действует. Пролонгирован"),
		WAITING_FOR_CLOSING_BY_CLIENT("Ожидает закрытия по инициативе ФЛ"),
		WAITING_FOR_CLOSING_BY_PLATFORM("Ожидает отмены по инициативе платформы"),
		WITHDRAWN_BY_BANK("Продукт отозван банком"),
		WITHDRAWN_BY_PLATFORM("Продукт отозван платформой"),
		DENIED_BY_BANK("Отказ банка"),
		CLOSED_EARLY_BY_CLIENT("Закрыт по инициативе ФЛ (досрочно)"),
		CANCELED_BY_PLATFORM("Отменен по инициативе платформы"),
		CANCELED_BY_BANK("Отменен по инициативе банка"),
		CLOSED_TERM_BY_BANK("Закрыт по инициативе банка (срок)");

		private final String name;
		Status(String s) {
			name = s;
		}

		@Override
		public String toString() {
			return this.name;
		}
	}

	public enum Action {
		MAKE_APPLICATION("ОФОРМИТЬ ЗАЯВКУ"),
		START_INITIAL_REPLENISHMENT("ВНЕСТИ СРЕДСТВА");

		private final String name;
		Action(String s) {
			name = s;
		}

		@Override
		public String toString() {
			return this.name;
		}
	}
}
