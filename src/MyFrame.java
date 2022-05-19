//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class MyFrame extends Component implements ActionListener {
//    JPanel homePanel = new JPanel();
//    JFrame homeFrame = new JFrame("Twitter Versus Home");
//    JTextField firstUserHandleInput = new JTextField("First User Handle", 15);
//    JTextField secondUserHandleInput = new JTextField("Second User Handle", 15);
//    JButton returnButton = new JButton("return");
//
//    public MyFrame() {
//        homeFrame.setSize(900, 600);
//        homeFrame.setVisible(true);
//
//        firstUserHandleInput.setSize(15, 10);
//        secondUserHandleInput.setSize(15, 10);
//
//        homeFrame.add(firstUserHandleInput);
//        homeFrame.add(secondUserHandleInput);
//        homePanel.add(returnButton);
//
//        homeFrame.setVisible(true);
//        homePanel.setVisible(true);
//
//        returnButton.addActionListener(new ActionListner() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                //UserHandleInput에 들어있던 두 String을 checkHandle로 확인
//                if (checkHandle()) {
//                    homeFrame.setVisible(false);
//                    displayResult();
//                }
//                else {
//                    //실패 메시지 띄우고 클릭 시 사라지게 유도
//                }
//            }
//        });
//    }
//
//    public void displayResult() {
//        //결과 화면 프레임 생성
//
//        //twitterVersusGUI에 필요한 함수들 죄다 넣어놓고 객체들을 받아와서 프레임에 추가하고 그대로 출력해주기
//
//        //뒤로가기 버튼을 만들어 액션리스너를 통해 홈화면으로 되돌아가기
//    }
//
//    public boolean checkHandle(String a, String b) {
//        //a와 b가 빈 문자열이 아니며 트위터 유저인지 확인
//    }
//}
