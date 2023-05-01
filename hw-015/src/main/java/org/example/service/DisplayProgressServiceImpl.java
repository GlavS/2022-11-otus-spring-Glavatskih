package org.example.service;

import org.springframework.stereotype.Component;

@Component
public class DisplayProgressServiceImpl implements DisplayProgressService {
    private static final int STOP_VALUE = 200;
    @Override
    public void progress(String message) {
        System.out.println(message);
        for (int i = 0; i <= STOP_VALUE; i += 10) {
            progressPercentage(i);
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                throw new RuntimeException("Exception running progress loop: " + e.getMessage());
            }
        }
    }

    private void progressPercentage(int remain) {
        if (remain > STOP_VALUE) {
            throw new IllegalArgumentException();
        }
        int maxBareSize = 10; // 10unit for 100%
        int remainProcent = ((100 * remain) / STOP_VALUE) / maxBareSize;
        char defaultChar = '-';
        String icon = "*";
        String bare = new String(new char[maxBareSize]).replace('\0', defaultChar) + "]";
        String bareDone = "[" +
                icon.repeat(Math.max(0, remainProcent));
        String bareRemain = bare.substring(remainProcent);
        System.out.print("\r" + bareDone + bareRemain + " " + remainProcent * 10 + "%");
        if (remain == STOP_VALUE) {
            System.out.print("\n");
        }
    }
}
