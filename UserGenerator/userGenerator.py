import random
import json
import array
import csv
# declare arrays of the character that we need in out password
# Represented as chars to enable easy string concatenation
MAX_LEN = 12
DIGITS = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9'] 
LOCASE_CHARACTERS = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
                     'i', 'j', 'k', 'm', 'n', 'o', 'p', 'q',
                     'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
                     'z']
 
UPCASE_CHARACTERS = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
                     'I', 'J', 'K', 'M', 'N', 'O', 'P', 'Q',
                     'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
                     'Z']
 
SYMBOLS = ['@', '#', '$', '%', '=', ':', '?', '.', '/', '|', '~', '>',
           '*', '(', ')', '<']

EXPERIENCE_RANK = ['Beginner', 'Intermediate', 'Advanced', 'Professional']

STATES = ['AL', 'AK', 'AZ', 'AR', 'CA', 'CO', 'CT', 'DE', 'FL', 'GA', 'HI', 'ID', 'IL', 'IN', 'IA', 'KS', 'KY', 'LA', 'ME', 'MD', 'MA', 'MI', 'MN', 'MS', 'MO', 'MT', 'NE', 'NV', 'NH', 'NJ', 'NM', 'NY', 'ND', 'NC', 'OH', 'OK', 'OR', 'PA', 'RI', 'SC', 'SD', 'TN', 'TX', 'UT', 'VT', 'VA', 'WA', 'WV', 'WI', 'WY']
EMAILSUFFIX = ["@gmail.com", "@outlook.com", "@163.com", "@hotmail.com", "@qq.com", "@acme.com"]
# combines all the character arrays above to form one array
COMBINED_LIST = DIGITS + UPCASE_CHARACTERS + LOCASE_CHARACTERS + SYMBOLS
EMAILPREFIX = DIGITS + UPCASE_CHARACTERS + LOCASE_CHARACTERS
def userGenerator():
    num = pow(10, 6)
    f = open("first-names.json")
    # m = open("middle-names.json")
    l = open("last-names.json")
    with open("addressList.text") as addr:
        lines = addr.readlines()
    firstNames = json.load(f)
    # middleNames = json.load(m)
    lastNames = json.load(l)
    # print(type(lines))
    w = open('users.csv', 'w')
    writer = csv.writer(w)
    writer.writerow(["uid", "firstName", "lastName", "gender", "age", "weight in kg", "height in cm", "phone", "password", "email", "address", "hikingLevel"])
    for i in range(num):
        uid = i
        age = random.randint(9, 80)
        weight = random.randint(30, 100)
        phone = ""
        for j in range(10):
            phone += random.choice(['0', '1', '2', '3', '4', '5', '6', '7', '8', '9'])
        gender = random.choice(["Female", "Male", "Not Specified", "Non-binary"])
        if gender == "female":
            height = int(abs(random.gauss(160, 15)))
            # weight = abs(random.gauss())
        else:
            height = int(abs(random.gauss(170, 15)))
        addressNum = random.randint(1, 5)
        address = ""
        for a in range(addressNum):
            address += str(random.randint(1, 9))
        line = random.choice(lines)
        address += " "
        info = line.split(" ")
        for s in range(1, len(info)-2):
            address += info[s]
            address += " "
        # address += " "
        address += random.choice(STATES)
        address += " "
        for c in range(5):
            address += str(random.randint(0, 9))
        email = ""
        emailPrefixLen = random.randint(5, 10)
        for k in range(emailPrefixLen):
            email += random.choice(EMAILPREFIX)
        email += random.choice(EMAILSUFFIX)
        
        skillLvl = random.choice(EXPERIENCE_RANK)

        # randomly select at least one character from each character set above
        rand_digit = random.choice(DIGITS)
        rand_upper = random.choice(UPCASE_CHARACTERS)
        rand_lower = random.choice(LOCASE_CHARACTERS)
        rand_symbol = random.choice(SYMBOLS)
        
        # combine the character randomly selected above
        # at this stage, the password contains only 4 characters but
        # we want a 12-character password
        temp_pass = rand_digit + rand_upper + rand_lower + rand_symbol
        
        
        # now that we are sure we have at least one character from each
        # set of characters, we fill the rest of
        # the password length by selecting randomly from the combined
        # list of character above.
        for x in range(MAX_LEN - 4):
            temp_pass = temp_pass + random.choice(COMBINED_LIST)
        
            # convert temporary password into array and shuffle to
            # prevent it from having a consistent pattern
            # where the beginning of the password is predictable
            temp_pass_list = array.array('u', temp_pass)
            random.shuffle(temp_pass_list)
        
        # traverse the temporary password array and append the chars
        # to form the password
        password = ""
        for x in temp_pass_list:
                password = password + x
         
        firstName = random.choice(firstNames)
        # middleName = random.choice(middleNames)
        lastName = random.choice(lastNames)
        # print(uid, firstName, lastName, gender, age, weight, height, phone, password, email, address)
        row = [uid, firstName, lastName, gender, age, weight, height, phone, password, email, address, skillLvl]
        writer.writerow(row)
userGenerator()