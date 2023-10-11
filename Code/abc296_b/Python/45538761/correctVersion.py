j_list=['a','b','c','d','e','f','g','h']
str_list = [input() for _ in range(8)]
counter = 0
for i in str_list:
    for j in range(8):
        if(i[j]=='*'):
            print(j_list[j]+str(8-counter))
            break
    counter += 1
