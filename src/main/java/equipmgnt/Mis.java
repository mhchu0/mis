package equipmgnt;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Mis_table")
public class Mis {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private Long approvalId;
        private Long equipmentId;
        private Integer qty;
        private String status;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        public Long getApprovalId() {
            return approvalId;
        }

        public void setApprovalId(Long approvalId) {
            this.approvalId = approvalId;
        }
        public Long getEquipmentId() {
            return equipmentId;
        }

        public void setEquipmentId(Long equipmentId) {
            this.equipmentId = equipmentId;
        }
        public Integer getQty() {
            return qty;
        }

        public void setQty(Integer qty) {
            this.qty = qty;
        }
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

}
