function result=simul2()

%Afirst

filename='outputTimes_Afirst.txt';
times=importdata(filename,',');
filename='outputA_Afirst.txt';
A=importdata(filename,',');
filename='outputB_Afirst.txt';
B=importdata(filename,',');

figure(1)
plot(times,(A+B));
figure(2);
plot(times,A);
figure(3);
plot(times,B);

% Bfirst
filename='outputTimes_Bfirst.txt';
times=importdata(filename,',');
filename='outputA_Bfirst.txt';
A=importdata(filename,',');
filename='outputB_Bfirst.txt';
B=importdata(filename,',');
figure(4)
plot(times,(A+B));
figure(5)
plot(times,A);
figure(6);
plot(times,B);
end