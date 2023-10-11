T = int(input())

for _ in range(T):
    case = input()
    length = len(case)
    
    max_val = int("9"*(length-1))
    
    for i in range(1, length):
        if length % i == 0:
            
            tmp = case[:i]
            tmp = "".join(tmp)
            
            val1 = int(tmp*(length//i))
            val2 = int(str(int(tmp)-1)*(length//i))
            
            if int(case) >= val1:
                max_val = max(max_val, val1)
                          
            if int(case) >= val2:
                max_val = max(max_val, val2)
            
            
    print(max_val)