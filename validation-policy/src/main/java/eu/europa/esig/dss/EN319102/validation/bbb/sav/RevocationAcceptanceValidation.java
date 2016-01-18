package eu.europa.esig.dss.EN319102.validation.bbb.sav;

import java.util.Date;

import eu.europa.esig.dss.EN319102.policy.ValidationPolicy;
import eu.europa.esig.dss.EN319102.policy.ValidationPolicy.Context;
import eu.europa.esig.dss.EN319102.validation.ChainItem;
import eu.europa.esig.dss.EN319102.validation.CryptographicCheck;
import eu.europa.esig.dss.EN319102.wrappers.DiagnosticData;
import eu.europa.esig.dss.EN319102.wrappers.RevocationWrapper;
import eu.europa.esig.dss.jaxb.detailedreport.XmlSAV;
import eu.europa.esig.jaxb.policy.CryptographicConstraint;

/**
 * 5.2.8 Signature acceptance validation (SAV) This building block covers any
 * additional verification to be performed on the signature itself or on the
 * attributes of the signature ETSI EN 319 132-1
 */
public class RevocationAcceptanceValidation extends AbstractAcceptanceValidation<RevocationWrapper> {

	public RevocationAcceptanceValidation(DiagnosticData diagnosticData, Date currentTime, RevocationWrapper timestamp, ValidationPolicy validationPolicy) {
		super(diagnosticData, timestamp, currentTime, validationPolicy);
	}

	@Override
	protected void initChain() {
		firstItem = timestampCryptographic();
	}

	private ChainItem<XmlSAV> timestampCryptographic() {
		CryptographicConstraint constraint = validationPolicy.getSignatureCryptographicConstraint(Context.REVOCATION);
		return new CryptographicCheck<XmlSAV>(result, token, currentTime, constraint);
	}

}
