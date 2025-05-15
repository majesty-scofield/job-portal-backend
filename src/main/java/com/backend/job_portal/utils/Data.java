package com.backend.job_portal.utils;

public class Data {
    public static String getOtpMessageBody(String name, String otp) {
        return """
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>OTP Verification</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    background-color: #f2f4f6;
                    margin: 0;
                    padding: 0;
                }
                .email-container {
                    max-width: 600px;
                    margin: 40px auto;
                    background-color: #ffffff;
                    padding: 30px;
                    border-radius: 8px;
                    box-shadow: 0 2px 5px rgba(0,0,0,0.1);
                }
                .header {
                    text-align: center;
                    color: #4CAF50;
                }
                .otp-code {
                    font-size: 24px;
                    font-weight: bold;
                    color: #333;
                    background-color: #f1f1f1;
                    padding: 10px 20px;
                    border-radius: 6px;
                    display: inline-block;
                    margin: 20px 0;
                }
                .message {
                    font-size: 16px;
                    color: #555;
                    margin-bottom: 30px;
                }
                .footer {
                    text-align: center;
                    font-size: 12px;
                    color: #888;
                    margin-top: 40px;
                }
            </style>
        </head>
        <body>
            <div class="email-container">
                <h2 class="header">Your OTP Code</h2>
                <p class="message">
                    Dear %s,<br><br>
                    Use the OTP below to verify your email address. This code is valid for the next 10 minutes. Do not share it with anyone.
                </p>
                <div class="otp-code">%s</div>
                <p class="message">
                    If you did not request this code, you can safely ignore this email.
                </p>
                <div class="footer">
                    &copy; 2025 SuperJobs. All rights reserved.
                </div>
            </div>
        </body>
        </html>
        """.formatted(name, otp);
    }
}
