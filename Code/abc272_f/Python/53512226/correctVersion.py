from atcoder.string import*
I=input
n=int(I())
Y=c=0
for i in suffix_array(I()*2+'`'+I()*2+'{'):c+=i<n;Y+=c*(2*n<i<=3*n)
print(Y)