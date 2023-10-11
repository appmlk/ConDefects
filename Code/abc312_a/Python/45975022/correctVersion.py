S = input()

Flag = 0

string = ['ACE', 'BDF', 'CEG', 'DFA', 'EGB', 'FAC', 'GBD']

for i in range(len(string)):
    if S == string[i]:
        Flag = 1

if Flag == 1:
    print('Yes')
else:
    print('No')
