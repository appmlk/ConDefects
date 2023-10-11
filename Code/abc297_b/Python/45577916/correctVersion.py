S = input()
K = S.find('K')
B1 = S.find('B')
B2 = S.rfind('B')
R1 = S.find('R')
R2 = S.rfind('R')

if ((B1+B2) % 2) != 0 and (R1 < K < R2):
    print('Yes')
else:
    print('No')
