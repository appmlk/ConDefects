#a = int(input());
list_A_B = list(map(int, input().split()));
A = list_A_B[0]
B = list_A_B[1]
#x = int(input());

if A%3 == 1 and B == A+1:
    print('Yes')
elif A%3 == 2 and B == A+1:
    print('Yes')
else:
    print('No')