package com.company.Personalmgmt.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "fixed_deposit_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "historyId")
public class FixedDepositHistory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "history_id", nullable = false)
	private Long historyId;

	@Column(name = "original_deposit_id")
	private Long originalDepositId;

	@Column(name = "deposit_account_no", length = 50)
	private String depositAccountNo;

	@Column(name = "bank_name", length = 100)
	private String bankName;

	@Column(name = "deposit_date")
	@Temporal(TemporalType.DATE)
	private Date depositDate;

	@Column(name = "principal_amount", precision = 15, scale = 2)
	private BigDecimal principalAmount;

	@Column(name = "fixed_interest_rate", precision = 5, scale = 2)
	private BigDecimal fixedInterestRate;

	@Column(name = "interest_amount", precision = 15, scale = 2)
	private BigDecimal interestAmount;

	@Column(name = "maturity_amount", precision = 15, scale = 2)
	private BigDecimal maturityAmount;

	@Column(name = "tax_amount", precision = 15, scale = 2)
	private BigDecimal taxAmount;

	@Column(name = "maturity_date")
	@Temporal(TemporalType.DATE)
	private Date maturityDate;

	@Column(name = "tenure_months")
	private Integer tenureMonths;

	@ManyToOne
	@JoinColumn(name = "month_id")
	private MonthMaster monthMaster;

	@ManyToOne
	@JoinColumn(name = "year_id")
	private YearMaster yearMaster;

	@Column(name = "moved_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date movedAt;

	@Column(name = "created_by", length = 100)
	private String createdBy;

	@Column(name = "updated_by", length = 100)
	private String updatedBy;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "nominee_name", length = 100)
	private String nomineeName;

	@Column(name = "remark", length = 100)
	private String remark;

	@Column(name = "renewed_to_id")
	private Long renewedToId;
}
