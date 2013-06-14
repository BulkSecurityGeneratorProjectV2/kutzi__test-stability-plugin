package de.esailors.jenkins.teststability;

import hudson.tasks.junit.TestAction;
import de.esailors.jenkins.teststability.StabilityTestData.CircularBuffer;

class StabilityTestAction extends TestAction {

	private CircularBuffer ringBuffer;
	private String description;

	public StabilityTestAction(CircularBuffer ringBuffer) {
		this.ringBuffer = ringBuffer;
		
		if (this.ringBuffer == null || this.ringBuffer.isEmpty()) {
			this.description = "No known failures";
		} else {
			int total = 0, failed = 0;
			for (boolean passed : this.ringBuffer.getData()) {
				total++;
				if (!passed) {
					failed++;
				}
			}
			this.description = "Failed " + failed + " times in the last " + total + " runs";
		}
	}

	public String getIconFileName() {
		// TODO Auto-generated method stub
		return null;
	}

	public CircularBuffer getRingBuffer() {
		return this.ringBuffer;
	}

	public String getDescription() {
		return this.description;
	}
	
	public String getDisplayName() {
		return "Test stability";
	}

	public String getUrlName() {
		return "stability";
	}
	
}