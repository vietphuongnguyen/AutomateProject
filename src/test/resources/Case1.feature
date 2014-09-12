Feature: Active account by sms

  Scenario: Input excel file content info to create account then change status of account to Waiting for user and active by sms syntax check info
    Given login drupal system with username and password
    When upload excel file
    Then input Define a task to process, Organization ID, Credit Group
    And change status to Authorized
    And click button Save task
    Then All accounts in excel file are created have status pending
    When Choose an account and change status to Waiting for user
    Then New account is created with status inactive in MCA_ADMIN sytem
    When user send sms with systax: "ECC tk" to 8099
    Then sms respone :"Mobivi: Tai khoan cua Quy Khach chua duoc kich hoat. Soan tin ECC XNDK<Activation code> gui 8099 de kich hoat. Ho tro mien phi 18006669."
    When user send sms with syntax: "ECC XNDK<Activation code>"
    Then system response: "Mobivi: Chuc mung ban da kich hoat tai khoan Phuc Loi iCare thanh cong. Ban duoc cap truoc so tien la xx,xxx,xxxd va co the su dung ngay cac dich vu NAP TIEN DT, UNG TIEN, CHUYEN TIEN VE QUE hoac MUA SAM TRA GOP KHONG LAI SUAT. Ho tro mien phi 18006669."
