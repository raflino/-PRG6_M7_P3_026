import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import HomeScreen from './screens/HomeScreen';
import HistoryScreen from './screens/HistoryScreen';
import AboutScreen from './screens/AboutScreen';

const Tab = createBottomTabNavigator();

export default function App() {
    return (
        <NavigationContainer>
            <Tab.Navigator>
                <Tab.Screen name="Scanner" component={HomeScreen} />
                <Tab.Screen name="History" component={HistoryScreen} />
                <Tab.Screen name="Profil" component={AboutScreen} />
            </Tab.Navigator>
        </NavigationContainer>
    );
}